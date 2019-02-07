package com.terwergreen.jvue.vue;

import com.alibaba.fastjson.JSON;
import com.terwergreen.jvue.utils.NashornUtil;
import com.terwergreen.jvue.utils.VueUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 渲染Vue
 */
public class VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
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
        // 编译Vue server
        engine.eval(VueUtil.readVueFile("server-bundle.js"));
        logger.info("Vue server编译成功，编译引擎为Nashorn");
    }

    public String renderContent(Map<String, Object> context) {
        try {
            logger.info("服务端调用renderServer前，设置路由上下文context:" + JSON.toJSONString(context));

            // 调用render方法返回promise
            ScriptObjectMirror promise = (ScriptObjectMirror) engine.callRender("renderServer", context);
            promise.callMember("then", fnResolve, fnRejected);
            promise.callMember("catch", fnRejected);

            int i = 0;
            int jsWaitTimeout = 1000 * 6;
            int interval = 200; // 等待时间间隔
            int totalWaitTime = 0; // 实际等待时间

            if (!promiseRejected) {
                while (!promiseResolved && totalWaitTime < jsWaitTimeout) {
                    // 执行nashornEventLoops.process()使主线程执行回调函数
                    engine.eval("global.nashornEventLoop.process();");
                    // ScriptObjectMirror nashornEventLoop = engine.getGlobalGlobalMirrorObject("nashornEventLoop");
                    // nashornEventLoop.callMember("process");
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        logger.error("Thread error:", e);
                    }
                    totalWaitTime = totalWaitTime + interval;
                    if (interval < 500) interval = interval * 2;
                    i = i + 1;
                }
            }
            engine.eval("global.nashornEventLoop.reset();");

            if (StringUtils.isEmpty(htmlObject)) {
                return "Server Timed Out";
            }
            logger.info("renderServer获取数据成功");
            logger.debug("htmlObject:" + JSON.toJSONString(htmlObject));

            // 处理返回结果
            if(htmlObject.size()==0){
                return "Server render error";
            }
            int status = (int) htmlObject.get("status");
            if (status == 0) {
                return (String) htmlObject.get("msg");
            } else {
                String outputHtml = (String) htmlObject.get("data");
                logger.debug("outputHtml:" + JSON.toJSONString(outputHtml));
                return outputHtml;
            }
        } catch (Exception e) {
            throw new IllegalStateException("failed to render vue component", e);
        }
    }
}