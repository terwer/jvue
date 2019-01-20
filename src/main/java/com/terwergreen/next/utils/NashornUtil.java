package com.terwergreen.next.utils;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptContext;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Nashorn工具类
 *
 * @author Terwer
 * @version 1.0
 * 2019/1/20 22:35
 **/
public class NashornUtil {
    private static final Logger logger = LoggerFactory.getLogger(NashornUtil.class);
    private static NashornUtil nashornUtil;
    private final NashornScriptEngine engine;
    private static ScriptContext sc = new SimpleScriptContext();
    private static ScheduledExecutorService globalScheduledThreadPool = Executors.newScheduledThreadPool(20);

    /**
     * Vue资源文件目录
     */
    private static final String LIB_DIR = "static/lib";
    private static final String POLYFILL_FILE_NAME = "nashorn-polyfill.js";

    public static synchronized NashornUtil getInstance() {
        if (nashornUtil == null) {
            long start = System.currentTimeMillis();
            nashornUtil = new NashornUtil();
            long end = System.currentTimeMillis();
            logger.info("init nashornHelper cost time {}ms", (end - start));
        }

        return nashornUtil;
    }

    private NashornUtil() {
        // 获取Javascript引擎
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        engine = (NashornScriptEngine) factory.getScriptEngine(new String[]{"--language=es6"});
        sc.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
        sc.setAttribute("__IS_SSR__", true, ScriptContext.ENGINE_SCOPE);
        sc.setAttribute("__NASHORN_POLYFILL_TIMER__", globalScheduledThreadPool, ScriptContext.ENGINE_SCOPE);
        engine.setBindings(sc.getBindings(ScriptContext.ENGINE_SCOPE), ScriptContext.ENGINE_SCOPE);

        try {
            // 编译nashorn-polyfill
            engine.eval(read(LIB_DIR + File.separator + POLYFILL_FILE_NAME));
//            for (String fileName : NashornUtil.VENDOR_FILE_NAME) {
//                engine.eval(read(SRC_DIR + File.separator + fileName));
//            }
//            engine.eval(read(SRC_DIR + File.separator + "app.js"));
            // 编译server
            engine.eval(VueUtil.readVueFile("server.js"));
            logger.info("Vue app.js编译成功，编译引擎为Nashorn");
        } catch (ScriptException e) {
            logger.error("Nashorn引擎Javascript解析错误", e);
        }
    }

    public NashornScriptEngine getNashornScriptEngine() {
        return engine;
    }

    public ScriptObjectMirror getGlobalGlobalMirrorObject(String objectName) {
        return (ScriptObjectMirror) engine.getBindings(ScriptContext.ENGINE_SCOPE).get(objectName);
    }

    public Object callRender(String methodName, Object... input) {
        try {
            return engine.invokeFunction(methodName, input);
        } catch (ScriptException e) {
            logger.error("run javascript failed.", e);
            return null;
        } catch (NoSuchMethodException e) {
            logger.error("no such method.", e);
            return null;
        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
