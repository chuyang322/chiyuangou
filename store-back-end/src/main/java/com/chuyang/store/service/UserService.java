package com.chuyang.store.service;

import com.chuyang.store.model.User;
import com.chuyang.store.utils.APIResult;


/**
 * @ClassName UserService
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/5/21 12:45
 * @Version 1.0
 **/
public interface UserService {
    public User getUserById(Integer id);
    public APIResult getUsers(int p,int size);
    public APIResult deleteUser(Integer id);
    public APIResult updateUser(User user);
    public APIResult updateAvatarById(Integer id,String filePath);
    public APIResult updatePasswordById(User user);
}
