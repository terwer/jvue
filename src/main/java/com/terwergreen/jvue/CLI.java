package com.terwergreen.jvue;

import com.terwergreen.jvue.vendor.vue.VueRenderer;
import com.terwergreen.jvue.vendor.vue.impl.VueRendererImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class CLI {
    private static final Log logger = LogFactory.getLog(CLI.class);

    public static void main(String[] args) {
        // 设置路由上下文
        Map<String, Object> httpContext = new HashMap<>();
        httpContext.put("url", "/about");

        // 渲染Vue
        VueRenderer vueRenderer = new VueRendererImpl();
        vueRenderer.renderContent(httpContext);
    }
}
