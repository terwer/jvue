package com.terwergreen.next.controller;

import com.terwergreen.next.vue.VueRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入口
 *
 * @author Terwer
 * @version 1.0
 * 2019/1/10 18:51
 **/
@Controller
public class MainController {
    private VueRenderer vueRenderer;

    @Autowired
    public MainController() {
        this.vueRenderer = new VueRenderer();
    }

    @RequestMapping("/")
    public String index(Model model) {
        String app = vueRenderer.renderContent();
        model.addAttribute("content", app);
        model.addAttribute("rnd", System.currentTimeMillis());
        return "index";
    }
}
