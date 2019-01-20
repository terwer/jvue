package com.terwergreen.next.vue;

import com.terwergreen.next.utils.NashornUtil;
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
    private String html = null;

    private Consumer<Object> fnResolve = object -> {
        synchronized (promiseLock) {
            html = (String) object;
            promiseResolved = true;
        }
    };

    public VueRenderer() {
        // 获取Javascript引擎
        engine = NashornUtil.getInstance();
    }

    public String renderContent() {
        try {
            ScriptObjectMirror promise = (ScriptObjectMirror) engine.callRender("renderServer");
            promise.callMember("then", fnResolve);
            ScriptObjectMirror nashornEventLoop = engine.getGlobalGlobalMirrorObject("nashornEventLoop");

            int i = 0;
            int jsWaitTimeout = 1000 * 60;
            int interval = 200; // 等待时间间隔
            int totalWaitTime = 0; // 实际等待时间
            while (!promiseResolved && totalWaitTime < jsWaitTimeout) {
                // 执行nashornEventLoops.process()使主线程执行回调函数
                nashornEventLoop.callMember("process");
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    logger.error("Thread error:", e);
                }
                totalWaitTime = totalWaitTime + interval;
                if (interval < 500) interval = interval * 2;
                i = i + 1;
            }
            return html;
        } catch (Exception e) {
            throw new IllegalStateException("failed to render vue component", e);
        }
    }
}
