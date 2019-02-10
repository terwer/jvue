package com.terwergreen.jvue.vue;

import com.terwergreen.jvue.vue.impl.VueRendererImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final Log logger = LogFactory.getLog(Main.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 设置路由上下文
        Map<String, Object> httpContext = new HashMap<>();
        httpContext.put("url", "/about");

        // 渲染Vue
        VueRenderer vueRenderer = new VueRendererImpl();
        vueRenderer.renderContent(httpContext);
        long end = System.currentTimeMillis();
        logger.info("Main.java cost {" + (end - start) + "} ms");
    }
}
