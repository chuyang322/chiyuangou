package com.chuyang.store.interceptor;

import com.chuyang.store.mapper.UserMapper;
import com.chuyang.store.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @ClassName JwtInterceptor
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/5/21 11:21
 * @Version 1.0
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, String> map=new HashMap<>();
        //从http请求头获取token
        String token = request.getHeader("token");

        System.out.println("拦截器");

        // 1、检查 token
        if (token == null || "".equals(token)) {
            return false;
        }

        // 2、验证 token
        if (!TokenUtil.verify(token)) {
            return false;
        }

        return true;
    }

}
