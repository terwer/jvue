package com.terwergreen.jvueserver.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
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
    private final Log logger = LogFactory.getLog(this.getClass());

    @RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
    public String index() {
        logger.info("enter main");
        return "index";
    }
}
