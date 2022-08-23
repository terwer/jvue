package com.terwergreen.jvueserver;

import com.terwergreen.jvueserver.constant.JVueConstants;
import com.terwergreen.jvueserver.coresevice.xmlrpc.MyXmlRpcServlet;
import com.terwergreen.jvueserver.util.SpringBeanUtils;
import org.apache.xmlrpc.webserver.XmlRpcServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author terwer
 */
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

    /**
     * 注册Xmlrpc
     */
    @Bean
    public ServletRegistrationBean registerServlet() {
        XmlRpcServlet xmlRpcServlet = new MyXmlRpcServlet();

        return new ServletRegistrationBean(
                xmlRpcServlet,
                JVueConstants.CONSTANT_XMLRPC_NAME // xml-rpc访问接口
        );
    }
}
