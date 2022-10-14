package com.chuyang.store.controller;

import com.chuyang.store.model.User;
import com.chuyang.store.service.UserService;
import com.chuyang.store.utils.APIResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/2 19:47
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public APIResult deleteUser(@PathVariable("id")Integer id){
        return userService.deleteUser(id);
    }

    @RequestMapping(value = "/users",method = RequestMethod.PUT)
    public APIResult updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     *
     * @param current
     * @param size
     * @return
     */
    @RequestMapping(value = "/list/{current}/{size}",method = RequestMethod.GET)
    public APIResult getUsers(@PathVariable("current") Integer current, @PathVariable("size") Integer size){
        return userService.getUsers(current,size);
    }

    @RequestMapping(value = "changePassword")
    public APIResult changePassword(@RequestBody User user){
        return userService.updatePasswordById(user);
    }
}
