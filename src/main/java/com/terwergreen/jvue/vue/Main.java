package com.terwergreen.jvue.vue;

import com.terwergreen.jvue.vue.impl.VueRendererImpl;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 设置路由上下文
        Map<String, Object> httpContext = new HashMap<>();
        httpContext.put("url", "/");

        // 渲染Vue
        VueRenderer vueRenderer = new VueRendererImpl();
        vueRenderer.renderContent(httpContext);
    }
}
