package com.terwergreen.jvueserver.config;

import com.terwergreen.jvueserver.interceptor.AuthHandlerInterceptor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * 静态资源配置
 *
 * @author Terwer
 * @version 1.0
 * 2019/1/11 12:25
 **/
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private static final Log logger = LogFactory.getLog(WebConfig.class);

    /**
     * 添加静态文件路径
     *
     * @param registry 资源注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("添加静态资源映射");
        // 静态资源映射
        // registry.addResourceHandler("/**").addResourceLocations("classpath:/");

        // swagger-ui
        logger.info("映射swagger-ui");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // webjars资源映射
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver())
                .addResolver(new PathResourceResolver());
    }

    /**
     * 配置拦截器
     *
     * @param registry InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authHandlerInterceptor()).addPathPatterns("/api/**");
    }

    /**
     * 接口访问，带token
     *
     * @return AuthHandlerInterceptor
     */
    @Bean
    public AuthHandlerInterceptor authHandlerInterceptor() {
        return new AuthHandlerInterceptor();
    }
}
