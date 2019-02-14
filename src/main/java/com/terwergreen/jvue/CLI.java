package com.terwergreen.jvue;

import com.alibaba.fastjson.JSON;
import com.terwergreen.jvue.vendor.vue.VueRenderer;
import com.terwergreen.jvue.vendor.vue.impl.VueRendererImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

public class CLI {
    private static final Log logger = LogFactory.getLog(CLI.class);

    /**
     * 运行命令
     * mvn -v && mvn compile && mvn exec:java
     *
     * @param args 参数
     */
    public static void main(String[] args) {
        // 设置路由上下文
        Map<String, Object> httpContext = new HashMap<>();
        httpContext.put("url", "/");

        // 渲染Vue
        VueRenderer vueRenderer = new VueRendererImpl();
        Map<String, Object> resultMap = vueRenderer.renderContentCLI(httpContext);
        logger.info("resultMap=>" + JSON.toJSONString(resultMap));
    }
}
