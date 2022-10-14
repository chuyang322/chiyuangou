package com.chuyang.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyang.store.mapper.UserMapper;
import com.chuyang.store.model.User;
import com.chuyang.store.service.UserService;
import com.chuyang.store.utils.APIResult;
import com.chuyang.store.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @ClassName UserServiceImpl
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/5/21 12:46
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public APIResult getUsers(int p, int size) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        Page<User> page = new Page(p, size);
        IPage<User> ipage = userMapper.selectPage(page, wrapper);
        APIResult apiResult = new APIResult();
        apiResult.setCode(200);
        apiResult.setMessage("查询成功");
        apiResult.setData(ipage);
        return apiResult;
    }

    @Override
    public APIResult deleteUser(Integer id) {
        int n=userMapper.deleteById(id);
        if(n!=0){
            return APIResult.createOKMessage("删除成功");
        }else{
            return APIResult.createNg("删除失败");
        }
    }

    @Override
    public APIResult updateUser(User user) {
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("id",user.getId());
        int n = userMapper.update(user,updateWrapper);
        if(n!=0){
            return APIResult.createOKMessage("修改成功");
        }else {
            return APIResult.createNg("修改失败");
        }
    }

    @Override
    public APIResult updateAvatarById(Integer id,String filePath) {
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("id",id).set("avatar",filePath);
        int n = userMapper.update(null,updateWrapper);
        if(n!=0&&filePath!=null){
            return APIResult.createOKMessage("设置成功");
        }else {
            return APIResult.createNg("设置失败");
        }
    }

    @Override
    public APIResult updatePasswordById(User user) {

        try {
            user.setPassword(MD5Util.MD5Password(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return APIResult.createNg("密码加密异常");
        }
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("id",user.getId()).set("password",user.getPassword());
        int n=userMapper.update(null,updateWrapper);
        if(n!=0){
            return APIResult.createOKMessage("修改成功，请重新登录");
        }else {
            return APIResult.createNg("修改失败");
        }

    }
}
