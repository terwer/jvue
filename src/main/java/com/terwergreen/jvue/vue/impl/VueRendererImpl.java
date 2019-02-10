package com.terwergreen.jvue.vue.impl;

import com.alibaba.fastjson.JSON;
import com.terwergreen.jvue.vue.JSContext;
import com.terwergreen.jvue.vue.VueRenderer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

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
@Service
@Scope("prototype")
public class VueRendererImpl implements VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
    // 是否显示错误到浏览器
    private static final Integer SHOW_SERVER_ERROR = 1;
    private Context context;

    private final Object promiseLock = new Object();
    private volatile boolean promiseResolved = false;
    private volatile boolean promiseRejected = false;

    private Object htmlObject = null;

    private Consumer<Object> fnResolve = object -> {
        synchronized (promiseLock) {
            htmlObject = object;
            promiseResolved = true;
            logger.info("fnResolve=>promiseResolved");
            logger.debug("htmlObject=>" + htmlObject);
        }
    };

    private Consumer<Object> fnRejected = object -> {
        synchronized (promiseLock) {
            htmlObject = object;
            promiseRejected = true;
            logger.info("fnRejected=>promiseRejected");
            logger.debug("htmlObject=>" + htmlObject);
        }
    };

    public VueRendererImpl() {
        // 获取Javascript引擎
        context = JSContext.getInstance().getContext();
        logger.info("初始化VueRender");
    }

    private void testExecute(Map<String, Object> httpContext) {
        try {
            String testSource = "(async()=>{" +
                    "const context = " + JSON.toJSONString(httpContext) + ";" +
                    "const promise = global.renderServer(context);" +
                    "console.log('promise=>', promise);" +
                    "promise.then(" +
                    "  resolve => {" +
                    "    console.log('resolve>>', JSON.stringify(resolve));" +
                    "  }," +
                    "  rejected => {" +
                    "    console.log('rejected>>', JSON.stringify(rejected));" +
                    "  }" +
                    ");" +
                    "})();";
            context.eval("js", testSource);
            logger.info("testExecute executed");
        } catch (Exception e) {
            logger.error("Vue testExecute error:", e);
        }
    }

    private void execute(Map<String, Object> httpContext) {
        try {
            String source = "(async()=>{" +
                    "const context = " + JSON.toJSONString(httpContext) + ";" +
                    "const promise = global.renderServer(context);" +
                    "console.log('promise=>', promise);" +
                    "return promise;" +
                    "})();";
            Value eval = context.eval("js", source);
            logger.info("eval=>" + eval);

            Value thenEval = eval.invokeMember("then", fnResolve, fnRejected);
            logger.info("thenEval=>" + thenEval);

            int i = 0;
            int jsWaitTimeout = 1000 * 2;
            int interval = 200; // 等待时间间隔
            int totalWaitTime = 0; // 实际等待时间

            if (!promiseResolved) {
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

                if (!promiseResolved) {
                    logger.error("time is out");
                } else {
                    logger.info("cost time to resolve:" + totalWaitTime);
                    logger.info("htmlObject get success");
                }
            } else {
                logger.info("promise already resolved");
                logger.info("htmlObject get success");
            }
        } catch (Exception e) {
            logger.error("Vue execute error:", e);
        }
    }

    @Override
    public Map<String, Object> renderContent(Map<String, Object> httpContext) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rnd", System.currentTimeMillis());
        resultMap.put("showError", SHOW_SERVER_ERROR);
        logger.info("服务端调用renderServer前，设置路由上下文context:" + JSON.toJSONString(httpContext));
        try {
            testExecute(httpContext);
            // execute(httpContext);

            // 处理返回结果
            if (null == htmlObject || StringUtils.isEmpty(htmlObject.toString())) {
                logger.error("500 Internal Server Error:Server render error,Timed out more than 60 seconds...");
                resultMap.put("renderStatus", 0);
                resultMap.put("content", "500 Internal Server Error:Server render error,Timed out more than 60 seconds...");
                return resultMap;
            }

            logger.info("renderServer获取数据成功");
            logger.debug("htmlObject:" + JSON.toJSONString(htmlObject));
            resultMap.put("renderStatus", 1);
            resultMap.put("content", htmlObject);
        } catch (Exception e) {
            resultMap.put("renderStatus", 0);
            resultMap.put("content", "failed to render vue component");
            logger.error("failed to render vue component", e);
        }
        return resultMap;
    }
}
