package com.terwergreen.jvue.controller;

import com.terwergreen.jvue.vendor.vue.VueRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @Autowired
    private VueRenderer vueRenderer;

    @RequestMapping("/")
    public String index(Model model) {
        // 设置路由上下文
        Map<String, Object> context = new HashMap<>();
        context.put("url", "/");

        Map<String, Object> resultMap = vueRenderer.renderContent(context);
        model.addAllAttributes(resultMap);
        return "index.ssr";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return index(model);
    }
}
