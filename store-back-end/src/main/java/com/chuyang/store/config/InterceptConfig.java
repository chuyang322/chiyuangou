package com.chuyang.store.config;

import com.chuyang.store.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptConfig
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/5/21 11:24
 * @Version 1.0
 **/
@Configuration
public class InterceptConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new JwtInterceptor())
                //拦截的路径 需要进行token验证的路径
                .addPathPatterns("/hello","/article/articles")
                //放行的路径
                .excludePathPatterns("/login","/verifySignCode","/article/list","/user/list","/user/users","/images/uploadImages","/goods/goods");
    }
}
