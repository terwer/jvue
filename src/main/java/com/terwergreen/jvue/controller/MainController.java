package com.terwergreen.jvue.controller;

import com.terwergreen.jvue.vue.VueRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
@Scope("prototype")
public class MainController {
    private VueRenderer vueRenderer;

    @Autowired
    public MainController() {
        this.vueRenderer = new VueRenderer();
    }

    @RequestMapping("/")
    public String index(Model model) {
        // 设置路由上下文
        Map<String, Object> context = new HashMap<>();
        context.put("url", "/");

        String app = vueRenderer.renderContent(context);
        model.addAttribute("content", app);
        model.addAttribute("rnd", System.currentTimeMillis());
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return index(model);
    }

    @RequestMapping("/post/{id}.html")
    public String post(Model model, @PathVariable String id) {
        // 设置路由上下文
        Map<String, Object> context = new HashMap<>();
        context.put("url", String.format("/post/%s.html", id));

        String app = vueRenderer.renderContent(context);
        model.addAttribute("content", app);
        model.addAttribute("rnd", System.currentTimeMillis());
        return "index";
    }
}
