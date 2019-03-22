package com.terwergreen.jvueserver.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共类 Controller
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/22 15:18
 **/
public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;
}
