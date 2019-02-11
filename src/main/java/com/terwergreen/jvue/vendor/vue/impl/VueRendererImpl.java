package com.terwergreen.jvue.vendor.vue.impl;

import com.alibaba.fastjson.JSON;
import com.eclipsesource.v8.JavaVoidCallback;
import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.Releasable;
import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Array;
import com.eclipsesource.v8.V8Object;
import com.terwergreen.jvue.servlet.JVueDispatcherServlet;
import com.terwergreen.jvue.util.VueUtil;
import com.terwergreen.jvue.vendor.vue.VueRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务端渲染Vue
 *
 * @author Terwer
 * @version 1.0
 * 2019/2/1 11:29
 **/
@Service
//@Scope("prototype")
public class VueRendererImpl implements VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
    // 是否显示错误到浏览器
    private static final Integer SHOW_SERVER_ERROR = 1;
    // 最长等待时间
    private static final Integer MAX_WAIT_SECONDS = 2;
    private V8 v8;
    private NodeJS nodeJS;

    private final Object callbackLock = new Object();
    private volatile boolean callbackResolved = false;
    private volatile boolean callbackRejected = false;

    private Map<String, Object> htmlMap = new HashMap<>();

    @Autowired
    private DispatcherServlet dispatcherServlet;

    public VueRendererImpl() {
        logger.info("初始化VueRender");
    }

    private void runMessageLoop() {
        boolean isRunning = nodeJS.isRunning();
        logger.info("NodeJS isRunning:" + isRunning);
        while (nodeJS.isRunning()) {
            nodeJS.handleMessage();
            logger.info("nodeJS is handling message...");
        }
    }

    private void executeV8(Map<String, Object> httpContext) {
        try {
            // 获取Javascript引擎
            if (v8 == null) {
                v8 = ((JVueDispatcherServlet) dispatcherServlet).getV8();
                nodeJS = ((JVueDispatcherServlet) dispatcherServlet).getNodeJS();

                JavaVoidCallback successCallback = (V8Object receiver, V8Array parameters) -> {
                    synchronized (callbackLock) {
                        if (parameters.length() > 0) {
                            callbackResolved = true;
                            V8Object callbackResult = (V8Object) parameters.get(0);

                            Integer status = callbackResult.getInteger("status");
                            String data = callbackResult.getString("data");
                            String msg = callbackResult.getString("msg");
                            htmlMap.put("status", status);
                            htmlMap.put("data", data);
                            htmlMap.put("msg", msg);

                            if (callbackResult instanceof Releasable) {
                                ((Releasable) callbackResult).release();
                            }
                        }
                        v8.executeScript("console.log('successCallback invoked');");
                    }
                };

                JavaVoidCallback errorCallback = (receiver, parameters) -> {
                    synchronized (callbackLock) {
                        if (parameters.length() > 0) {
                            callbackRejected = true;
                            V8Object callbackResult = (V8Object) parameters.get(0);

                            Integer status = callbackResult.getInteger("status");
                            String data = callbackResult.getString("data");
                            String msg = callbackResult.getString("msg");
                            htmlMap.put("status", status);
                            htmlMap.put("data", data);
                            htmlMap.put("msg", msg);

                            if (callbackResult instanceof Releasable) {
                                ((Releasable) callbackResult).release();
                            }
                        }
                        v8.executeScript("console.error('errorCallback invoked');");
                    }
                };

                v8.registerJavaMethod(successCallback, "onServerRenderSuccess");
                v8.registerJavaMethod(errorCallback, "onServerRenderError");

                logger.info("获取v8");
                v8.getLocker().release();
            }
            v8.getLocker().acquire();

            File entryServerFile = VueUtil.readVueFile("entry-server.js");
            nodeJS.exec(entryServerFile);

            runMessageLoop();

            String testSource = "var context = {url:'" + httpContext.getOrDefault("url", "") + "'};renderServer(context);";
            logger.info("testSource=>" + testSource);
            v8.executeScript(testSource);

            runMessageLoop();

            int i = 0;
            int jsWaitTimeout = 1000 * MAX_WAIT_SECONDS;
            int interval = 200; // 等待时间间隔
            int totalWaitTime = 0; // 实际等待时间

            if (!callbackResolved) {
                while (!callbackResolved && totalWaitTime < jsWaitTimeout) {
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        logger.error("Thread error:", e);
                    }
                    totalWaitTime = totalWaitTime + interval;
                    if (interval < 500) interval = interval * 2;
                    i = i + 1;
                }

                if (!callbackResolved) {
                    logger.error("time is out");
                } else {
                    logger.info("cost time to resolve:" + totalWaitTime);
                }
            } else {
                logger.info("promise already resolved");
            }
        } catch (Exception e) {
            logger.error("Vue executeV8 error:", e);
        }

        v8.getLocker().release();
        logger.info("entry-server.js执行完成");
    }

    @Override
    public Map<String, Object> renderContent(Map<String, Object> httpContext) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rnd", System.currentTimeMillis());
        resultMap.put("showError", SHOW_SERVER_ERROR);
        logger.info("服务端调用renderServer前，设置路由上下文context:" + JSON.toJSONString(httpContext));
        try {
            executeV8(httpContext);

            // 处理返回结果
            if (!callbackResolved && htmlMap.size() == 0) {
                String errorMessage = "Server render error,Timed out more than " + MAX_WAIT_SECONDS + " seconds...";
                logger.error(errorMessage);
                resultMap.put("renderStatus", 0);
                resultMap.put("content", errorMessage);
                return resultMap;
            }

            logger.info("renderServer获取数据成功");
            logger.debug("htmlMap:" + htmlMap);

            Integer renderStatus = Integer.parseInt(htmlMap.get("status").toString());
            String content = String.valueOf(htmlMap.get("data"));
            String message = String.valueOf(htmlMap.get("msg"));
            resultMap.put("renderStatus", renderStatus);
            resultMap.put("content", content);
            resultMap.put("message", message);
        } catch (Exception e) {
            resultMap.put("renderStatus", 0);
            resultMap.put("content", "failed to render vue component");
            logger.error("failed to render vue component", e);
        }
        return resultMap;
    }
}
