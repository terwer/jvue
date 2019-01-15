package com.terwergreen.next.vue;

import com.terwergreen.next.utils.VueUtil;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

public class VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
    private Object renderServerFunction;

    public VueRenderer() {
        try {
            // 获取Javascript引擎
            NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
            NashornScriptEngine engine = (NashornScriptEngine) factory.getScriptEngine(new String[]{"--language=es6"});
            // 编译
            // CompiledScript compiled = engine.compile(VueUtil.readVueFile("server-bundle.js"));
            CompiledScript   compiled = engine.compile(VueUtil.readVueFile("app.*.js"));
            logger.info("Vue app.js编译成功，编译引擎为Nashorn");
            this.renderServerFunction = compiled.eval();
        } catch (ScriptException e) {
            logger.error("Nashorn引擎Javascript解析错误", e);
            throw new RuntimeException(e);
        }
    }

    public String renderContent() {
        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        try {
            ScriptContext newContext = new SimpleScriptContext();
            newContext.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
            Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
            engineScope.put("renderServer", this.renderServerFunction);
            engine.setContext(newContext);
            Object html = engine.invokeFunction("renderServer");
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("failed to render vue component", e);
        }
    }

}
