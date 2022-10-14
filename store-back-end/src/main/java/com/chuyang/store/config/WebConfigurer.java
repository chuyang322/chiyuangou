package com.chuyang.store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebConfigurer
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/6 0:05
 * @Version 1.0
 **/
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/root/images/","file:C://images/");
    }
}
