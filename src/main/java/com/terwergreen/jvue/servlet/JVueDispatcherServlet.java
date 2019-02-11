package com.terwergreen.jvue.servlet;

import com.eclipsesource.v8.NodeJS;
import com.eclipsesource.v8.V8;
import com.terwergreen.jvue.vendor.j2v8.V8Context;
import com.terwergreen.jvue.vendor.j2v8.impl.V8ContextImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Terwer
 * @Date 2018/7/25 14:44
 * @Version 1.0
 * @Description 自定义DispatcherServlet
 **/

/**
 * Spring Boot为了添加你自己的servlet，需声明一个Servlet类型的@Bean，并给它特定的bean名称dispatcherServlet（如果只想关闭但不替换它，你可以使用该名称创建不同类型的bean）。
 */
public class JVueDispatcherServlet extends DispatcherServlet implements V8Context {
    private Logger logger = LogManager.getLogger(this.getClass());

    private V8Context v8Context;

    @Override
    public V8 getV8() {
        V8 v8= v8Context.getV8();
        v8.getLocker().acquire();
        return v8;
    }

    @Override
    public NodeJS getNodeJS() {
        return v8Context.getNodeJS();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 在这里初始化v8和nodejs
        logger.info("servlet初始化完毕，开始初始化v8和nodejs...");
        v8Context = new V8ContextImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        boolean isGet = HttpMethod.GET.toString().equals(method);
        logger.debug("JVueDispatcherServlet.service,process request:" + request.getRequestURL().toString() + ",queryString:" + request.getQueryString());
        super.service(request, response);
    }
}
