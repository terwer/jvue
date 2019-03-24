package com.terwergreen.jvueserver.api;

import com.terwergreen.jvueserver.util.SystemCache;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共类 Controller
 *
 * @author Terwer
 * @version 1.0
 * 2019/3/22 15:18
 **/
public abstract class BaseApi {
    @Autowired
    protected HttpServletRequest request;

    protected SystemCache cache = SystemCache.instance();
}
