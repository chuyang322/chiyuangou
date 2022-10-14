package com.chuyang.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chuyang.store.mapper.UserMapper;
import com.chuyang.store.model.User;
import com.chuyang.store.service.LoginService;
import com.chuyang.store.utils.APIResult;
import com.chuyang.store.utils.MD5Util;
import com.chuyang.store.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/5/15 15:23
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public APIResult login(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.eq("name",user.getName());
        try {
            user.setPassword(MD5Util.MD5Password(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return APIResult.createNg("密码加密异常");
        }
        User user1= userMapper.selectOne(queryWrapper);
        if(user1.getPassword().equals(user.getPassword())){
            // 设置token
            String token= TokenUtil.getToken(user.getId(),user.getName());
            // 设置接口返回结果
            user1.setPassword("");
            APIResult apiResult=new APIResult();
            HashMap data=new HashMap<>();
            data.put("user",user1);
            data.put("token",token);
            apiResult.setData(data);
            apiResult.setMessage("登录成功");
            apiResult.setCode(200);
            return apiResult;
        }else{
            return APIResult.createNg("密码错误");
        }
    }

    @Override
    public APIResult register(User user) {
        APIResult apiResult=nameCheck(user.getName());
        if(apiResult.getCode()!=200){
            return apiResult;
        }
        try {
            user.setPassword(MD5Util.MD5Password(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return APIResult.createNg("密码加密异常");
        }
        user.setCreateTime(System.currentTimeMillis());
        int n=userMapper.insert(user);
        if(n==1){
            return APIResult.createOKMessage("注册成功");
        }else {
            return APIResult.createNg("注册失败");
        }

    }

    @Override
    public APIResult nameCheck(String name) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        User user=userMapper.selectOne(queryWrapper);
        if(user==null){
            return APIResult.createOKMessage("用户名可用");
        }else {
            return APIResult.createNg("用户名已被注册");
        }
    }


}
