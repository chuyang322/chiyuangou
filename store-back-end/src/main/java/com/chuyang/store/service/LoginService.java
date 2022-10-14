package com.chuyang.store.service;

import com.chuyang.store.model.User;
import com.chuyang.store.utils.APIResult;

public interface LoginService {
    public APIResult login(User user);
    public APIResult register(User user);
    public APIResult nameCheck(String name);
}
