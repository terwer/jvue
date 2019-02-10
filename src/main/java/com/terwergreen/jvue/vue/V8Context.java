package com.terwergreen.jvue.vue;

import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class V8Context {
    private static final Log logger = LogFactory.getLog(JSContext.class);
    private static V8Context v8Context;
    private static V8 v8;
   private static NodeJS nodeJS;

    public static synchronized V8Context getInstance() {
        if (v8Context == null || v8 == null) {
            long start = System.currentTimeMillis();
            v8Context = new V8Context();
            v8 = V8.createV8Runtime();
            nodeJS = NodeJS.createNodeJS();
            long end = System.currentTimeMillis();
            logger.info("init V8Context cost time {" + (end - start) + "} ms");
        }
        return v8Context;
    }

    public V8 getV8() {
        return v8;
    }

    public NodeJS getNodeJS() {
        return nodeJS;
    }

    private V8Context() {
    }
}
