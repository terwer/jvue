package com.terwergreen.next.vue;

import com.terwergreen.next.utils.NashornUtil;
import com.terwergreen.next.utils.VueUtil;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

    private Object html = null;

    private Consumer<Object> fnResolve = object -> {
        synchronized (promiseLock) {
            html = object;
            promiseResolved = true;
        }
    };

    private Consumer<Object> fnRejected = object -> {
        synchronized (promiseLock) {
            html = object;
            promiseRejected = true;
        }
    };

    public VueRenderer() {
        // 获取Javascript引擎
        engine = NashornUtil.getInstance();
        // 编译Vue server
        engine.eval(VueUtil.readVueFile("server-bundle.js"));
        logger.info("Vue server编译成功，编译引擎为Nashorn");
    }

    public String renderContent() {
        try {
            ScriptObjectMirror promise = (ScriptObjectMirror) engine.callRender("renderServer", "{url:\"/\"}");
            promise.callMember("then", fnResolve, fnRejected);
            promise.callMember("catch", fnRejected);

            int i = 0;
            int jsWaitTimeout = 1000 * 6;
            int interval = 200; // 等待时间间隔
            int totalWaitTime = 0; // 实际等待时间

            logger.info("rejected status:" + promiseRejected);
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
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("failed to render vue component", e);
        }
    }
}
