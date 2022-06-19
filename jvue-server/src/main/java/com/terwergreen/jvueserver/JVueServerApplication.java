package com.terwergreen.jvueserver;

import com.terwergreen.jvueserver.constant.JVueConstants;
import com.terwergreen.jvueserver.util.SpringBeanUtils;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JVueServerApplication {

    public static void main(String[] args) {
        //设置应用类型
        SpringApplication springApplication = new SpringApplication(JVueServerApplication.class);
        // 启动Spring Boot
        ConfigurableApplicationContext applicationContext = springApplication.run(args);
        // 提供给上下文工具
        SpringBeanUtils.setContext(applicationContext);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Bean
    public ServletRegistrationBean registerServlet() {
        return new ServletRegistrationBean(
                new XmlRpcServlet(),
                JVueConstants.CONSTANT_XMLRPC_NAME // xml-rpc访问接口
        );
    }
}
