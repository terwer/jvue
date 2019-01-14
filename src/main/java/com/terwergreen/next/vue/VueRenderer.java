package com.terwergreen.next.vue;

import com.terwergreen.next.utils.VueUtil;
import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class VueRenderer {
    private final Log logger = LogFactory.getLog(this.getClass());
    //private Object renderServerFunction;

    public VueRenderer() {
        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        try {
            CompiledScript compiled = engine.compile(VueUtil.readVueFile("app.*.js"));
            logger.info("Vue app.js编译成功，编译引擎为Nashorn");
            // this.renderServerFunction =
            compiled.eval();
        } catch (ScriptException e) {
            logger.error("Nashorn引擎Javascript解析错误", e);
            throw new RuntimeException(e);
        }
    }
}
