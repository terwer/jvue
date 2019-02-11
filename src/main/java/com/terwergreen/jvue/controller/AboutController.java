package com.terwergreen.jvue.controller;

import com.terwergreen.jvue.vendor.vue.VueRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 关于
 *
 * @author Terwer
 * @version 1.0
 * 2019/2/1 11:45
 **/
@Controller
public class AboutController {
    @Autowired
    private VueRenderer vueRenderer;

    @RequestMapping("/about")
    public String about(Model model) {
        // 设置路由上下文
        Map<String, Object> context = new HashMap<>();
        context.put("url", "/about");

        Map<String, Object> resultMap = vueRenderer.renderContent(context);
        model.addAllAttributes(resultMap);
        return "index.ssr";
    }
}
