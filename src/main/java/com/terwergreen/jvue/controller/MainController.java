package com.terwergreen.jvue.controller;

import com.terwergreen.jvue.util.VueUtil;
import com.terwergreen.jvue.vendor.vue.VueRenderer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 入口
 *
 * @author Terwer
 * @version 1.0
 * 2019/1/10 18:51
 **/
@Controller
public class MainController {
    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private VueRenderer vueRenderer;

    @RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String index(Model model) {
        // 设置路由上下文
        Map<String, Object> httpContext = new HashMap<>();
        httpContext.put("url", "/");

        // 添加seo
        httpContext.put("title", "title");
        Map<String, Object> metaMap = new HashMap<>();
        metaMap.put("keywords", "keywords");
        metaMap.put("description", "description");
        httpContext.put("meta", metaMap);

        // 返回服务端渲染后的结果
        Map<String, Object> resultMap = vueRenderer.renderContent(httpContext);
        return VueUtil.resultMapToString(resultMap);
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return index(model);
    }
}
