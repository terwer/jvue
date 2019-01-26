package com.terwergreen.jvue.controller;

import com.terwergreen.jvue.vue.VueRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

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
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double width = screenSize.getWidth();
//        double height = screenSize.getHeight();

        String app = vueRenderer.renderContent();
        model.addAttribute("content", app);
        model.addAttribute("rnd", System.currentTimeMillis());
        return "index";
    }
}
