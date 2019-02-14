package com.terwergreen.jvue.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义DispatcherServlet
 * Spring Boot为了添加你自己的servlet，需声明一个Servlet类型的@Bean，并给它特定的bean名称dispatcherServlet（如果只想关闭但不替换它，你可以使用该名称创建不同类型的bean）。
 *
 * @author Terwer
 * @version 1.0
 * 2018/7/25 14:44
 */
public class JVueDispatcherServlet extends DispatcherServlet {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        boolean isGet = HttpMethod.GET.toString().equals(method);
        logger.debug("JVueDispatcherServlet.service,process request:" + request.getRequestURL().toString() + ",queryString:" + request.getQueryString());
        super.service(request, response);
    }
}
