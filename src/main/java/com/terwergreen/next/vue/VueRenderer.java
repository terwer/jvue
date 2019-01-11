package com.terwergreen.next.vue;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class VueRenderer {
    private Object renderServerFunction;

    public VueRenderer() {
//        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
//        try {
//            CompiledScript compiled = engine.compile(read("META-INF/resources/dist/js/app.8756476b.js"));
//            this.renderServerFunction = compiled.eval();
//        } catch (ScriptException e) {
//            throw new RuntimeException(e);
//        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
