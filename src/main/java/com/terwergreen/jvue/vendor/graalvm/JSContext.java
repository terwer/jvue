package com.terwergreen.jvue.vendor.graalvm;

import com.terwergreen.jvue.util.VueUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.graalvm.polyglot.Context;

public class JSContext {
    private static final Log logger = LogFactory.getLog(JSContext.class);
    private static JSContext jsContext;
    private static Context context;

    public static synchronized JSContext getInstance() {
        if (jsContext == null) {
            long start = System.currentTimeMillis();
            jsContext = new JSContext();
            context = Context.newBuilder("js").allowAllAccess(true).build();
            long end = System.currentTimeMillis();
            logger.info("init JSContext cost time {" + (end - start) + "} ms");

            // 编译entry-server
            String entryServer = VueUtil.readVueResourceString("entry-server.js");
            context.eval("js", entryServer);
            logger.info("编译entry-server.js完成");
        }
        return jsContext;
    }

    private JSContext() {
    }

    public Context getContext() {
        return context;
    }
}
