package com.terwergreen.jvue.vue;

import com.alibaba.fastjson.JSON;
import com.terwergreen.jvue.utils.NashornUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 服务端渲染Vue
 *
 * @author Terwer
 * @version 1.0
 * 2019/2/1 11:29
 **/
public class VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
    // 是否显示错误到浏览器
    private static final Integer SHOW_SERVER_ERROR = 1;
    private NashornUtil engine;

    private final Object promiseLock = new Object();
    private volatile boolean promiseResolved = false;
    private volatile boolean promiseRejected = false;

    private ScriptObjectMirror htmlObject = null;

    private Consumer<Object> fnResolve = object -> {
        synchronized (promiseLock) {
            htmlObject = (ScriptObjectMirror) object;
            promiseResolved = true;
            logger.info("promiseResolved");
        }
    };

    private Consumer<Object> fnRejected = object -> {
        synchronized (promiseLock) {
            htmlObject = (ScriptObjectMirror) object;
            promiseRejected = true;
            logger.info("promiseRejected");
        }
    };

    public VueRenderer() {
        // 获取Javascript引擎
        engine = NashornUtil.getInstance();
        engine.eval("console.log('engine start in spring');");
    }

    public Map<String, Object> renderContent(Map<String, Object> context) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rnd", System.currentTimeMillis());
        resultMap.put("showError", SHOW_SERVER_ERROR);

        ScriptObjectMirror nashornEventLoop = null;
        try {
            logger.info("服务端调用renderServer前，设置路由上下文context:" + JSON.toJSONString(context));

            promiseResolved = false;

            // 调用render方法返回promise
            ScriptObjectMirror promise = (ScriptObjectMirror) engine.callRender("renderServer", context);
            logger.debug("promise" + JSON.toJSONString(promise));
            promise.callMember("then", fnResolve, fnRejected);

            // 执行nashornEventLoops.process()使主线程执行回调函数
            // engine.eval("global.nashornEventLoop.process();");
            nashornEventLoop = engine.getGlobalGlobalMirrorObject("nashornEventLoop");
            nashornEventLoop.callMember("process");

            int i = 0;
            int jsWaitTimeout = 1000 * 2;
            int interval = 200; // 等待时间间隔
            int totalWaitTime = 0; // 实际等待时间

            if (!promiseRejected) {
                while (!promiseResolved && totalWaitTime < jsWaitTimeout) {
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        logger.error("Thread error:", e);
                    }
                    totalWaitTime = totalWaitTime + interval;
                    if (interval < 500) interval = interval * 2;
                    i = i + 1;
                }

                // 处理返回结果
                if (CollectionUtils.isEmpty(htmlObject)) {
                    logger.error("500 Internal Server Error:Server render error,Timed out more than 60 seconds...");
                    resultMap.put("renderStatus", 0);
                    resultMap.put("content", "500 Internal Server Error:Server render error,Timed out more than 60 seconds...");
                    return resultMap;
                }

                logger.info("renderServer获取数据成功");
                logger.debug("htmlObject:" + JSON.toJSONString(htmlObject));
                int status = (int) htmlObject.get("status");
                logger.info("msg:" + htmlObject.get("msg"));
                if (status == 0) {
                    resultMap.put("renderStatus", 0);
                    resultMap.put("content", htmlObject.get("msg"));
                    return resultMap;
                } else {
                    String outputHtml = (String) htmlObject.get("data");
                    logger.debug("outputHtml:" + JSON.toJSONString(outputHtml));
                    resultMap.put("renderStatus", 1);
                    resultMap.put("content", outputHtml);
                }
            } else {
                if (CollectionUtils.isEmpty(htmlObject)) {
                    resultMap.put("renderStatus", 0);
                    resultMap.put("content", "500 Internal Server Error:promiseRejected");
                    logger.error("500 Internal Server Error:promiseRejected");
                } else {
                    resultMap.put("renderStatus", 0);
                    resultMap.put("content", htmlObject.get("data"));
                }
            }
        } catch (Exception e) {
            resultMap.put("renderStatus", 0);
            resultMap.put("content", "failed to render vue component");
            logger.error("failed to render vue component", e);
        } finally {
            // reset nashornEventLoop
            logger.info("reset nashornEventLoop");
            // engine.eval("global.nashornEventLoop.reset();");
            nashornEventLoop.callMember("reset");
        }
        return resultMap;
    }
}