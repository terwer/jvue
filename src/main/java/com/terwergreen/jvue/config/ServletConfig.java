package com.terwergreen.jvue.config;

import com.terwergreen.jvue.servlet.JVueDispatcherServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @Author Terwer
 * @Date 2018/6/29 15:03
 * @Version 1.0
 * @Description 项目自定义配置
 **/
@Configuration
public class ServletConfig {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Bean
    public DispatcherServlet dispatcherServlet() {
        logger.info("注册JVueDispatcherServlet");
        return new JVueDispatcherServlet();
    }

    @Bean
    public DispatcherServletPath dispatcherServletPath(){
        logger.info("注册DispatcherServletPath");
        return new DispatcherServletRegistrationBean(dispatcherServlet(), "/*");
    }

    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        logger.info("注册DispatcherServletRegistrationBean");
        ServletRegistrationBean registration = (ServletRegistrationBean)dispatcherServletPath();
        registration.setLoadOnStartup(0);
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }
}
