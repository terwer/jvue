package com.terwergreen.jvue.vue.impl;

import com.alibaba.fastjson.JSON;
import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8;
import com.terwergreen.jvue.vue.V8Context;
import com.terwergreen.jvue.vue.VueRenderer;
import com.terwergreen.jvue.vue.VueUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
//@Scope("prototype")
public class VueRendererImpl implements VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
    // 是否显示错误到浏览器
    private static final Integer SHOW_SERVER_ERROR = 1;
    // private Context context;
    private V8 v8;
    private NodeJS nodeJS;

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
        // context = JSContext.getInstance().getContext();
        v8 = V8Context.getInstance().getV8();
        nodeJS = V8Context.getInstance().getNodeJS();
        logger.info("初始化VueRender");
    }

//    private void testExecute(Map<String, Object> httpContext) {
//        try {
//            String testSource = "(async()=>{" +
//                    "const context = " + JSON.toJSONString(httpContext) + ";" +
//                    "const promise = global.renderServer(context);" +
//                    "console.log('promise=>', promise);" +
//                    "promise.then(" +
//                    "  resolve => {" +
//                    "    console.log('resolve>>', JSON.stringify(resolve));" +
//                    "  }," +
//                    "  rejected => {" +
//                    "    console.log('rejected>>', JSON.stringify(rejected));" +
//                    "  }" +
//                    ");" +
//                    "})();";
//            context.eval("js", testSource);
//            logger.info("testExecute executed");
//        } catch (Exception e) {
//            logger.error("Vue testExecute error:", e);
//        }
//    }

//    private void execute(Map<String, Object> httpContext) {
//        try {
//            String source = "(async()=>{" +
//                    "const context = " + JSON.toJSONString(httpContext) + ";" +
//                    "const promise = global.renderServer(context);" +
//                    "console.log('promise=>', promise);" +
//                    "return promise;" +
//                    "})();";
//            Value eval = context.eval("js", source);
//            logger.info("eval=>" + eval);
//
//            Value thenEval = eval.invokeMember("then", fnResolve, fnRejected);
//            logger.info("thenEval=>" + thenEval);
//
//            int i = 0;
//            int jsWaitTimeout = 1000 * 2;
//            int interval = 200; // 等待时间间隔
//            int totalWaitTime = 0; // 实际等待时间
//
//            if (!promiseResolved) {
//                while (!promiseResolved && totalWaitTime < jsWaitTimeout) {
//                    try {
//                        Thread.sleep(interval);
//                    } catch (InterruptedException e) {
//                        logger.error("Thread error:", e);
//                    }
//                    totalWaitTime = totalWaitTime + interval;
//                    if (interval < 500) interval = interval * 2;
//                    i = i + 1;
//                }
//
//                if (!promiseResolved) {
//                    logger.error("time is out");
//                } else {
//                    logger.info("cost time to resolve:" + totalWaitTime);
//                    logger.info("htmlObject get success");
//                }
//            } else {
//                logger.info("promise already resolved");
//                logger.info("htmlObject get success");
//            }
//        } catch (Exception e) {
//            logger.error("Vue execute error:", e);
//        }
//    }

    private void runMessageLoop() {
        while (nodeJS.isRunning()) {
            nodeJS.handleMessage();
        }
    }

    private static File createTemporaryScriptFile(final String script, final String name) {
        File tempFile = null;
        PrintWriter writer = null;
        try {
            tempFile = File.createTempFile(name, ".js.tmp");
            writer = new PrintWriter(tempFile, "UTF-8");
            writer.print(script);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        return tempFile;
    }


    private void executeV8(Map<String, Object> httpContext) {
        boolean isRunning = nodeJS.isRunning();
        logger.info("NodeJS isRunning:" + isRunning);

        File entryServerFile = VueUtil.readVueFile("entry-server.js");
        nodeJS.exec(entryServerFile);
        runMessageLoop();

        File testScript = createTemporaryScriptFile("global.passed = true;", "testScript");
        nodeJS.require(testScript);
        runMessageLoop();

        Object test = nodeJS.getRuntime().executeScript(""
                + "var hello = 'hello, ';"
                + "var world = 'world!';"
                + "hello.concat(world).length;");
        logger.info("test=>" + test);

        String testSource = "(function() {" +
                "  var context = {" +
                "    url: '/about'" +
                "  };" +
                "  var promise = renderServer(context);" +
                "  console.log('promise=>', promise);" +
                "  return promise;" +
                "})();";

        Object testSourceResult = nodeJS.getRuntime().executeScript(testSource);
        logger.info("testSourceResult=>" + JSON.toJSONString(testSourceResult));

        // nodeJS.getRuntime().executeFunction("then",null);

        logger.info("entry-server.js执行完成");
    }

    @Override
    public Map<String, Object> renderContent(Map<String, Object> httpContext) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("rnd", System.currentTimeMillis());
        resultMap.put("showError", SHOW_SERVER_ERROR);
        logger.info("服务端调用renderServer前，设置路由上下文context:" + JSON.toJSONString(httpContext));
        try {
            // testExecute(httpContext);
            // execute(httpContext);
            executeV8(httpContext);

            // 处理返回结果
            if (promiseRejected || null == htmlObject || StringUtils.isEmpty(htmlObject.toString())) {
                logger.error("500 Internal Server Error:Server render error,Timed out more than 60 seconds...");
                resultMap.put("renderStatus", 0);
                resultMap.put("content", "500 Internal Server Error:Server render error,Timed out more than 60 seconds...");
                return resultMap;
            }

            logger.info("renderServer获取数据成功");
            String jsonContent = JSON.toJSONString(htmlObject);
            logger.debug("htmlObject:" + jsonContent);

            Map<?, ?> jsonMap = JSON.parseObject(jsonContent, Map.class);
            Integer renderStatus = Integer.parseInt(jsonMap.get("status").toString());
            String content = String.valueOf(jsonMap.get("data"));
            String message = String.valueOf(jsonMap.get("msg"));
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
