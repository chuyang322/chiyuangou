package com.chuyang.store.controller;

import com.chuyang.store.model.SignCode;
import com.chuyang.store.model.User;
import com.chuyang.store.service.LoginService;
import com.chuyang.store.utils.APIResult;
import com.chuyang.store.utils.SignCodeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public APIResult login(@RequestBody User user){
        APIResult apiResult=loginService.login(user);
        return apiResult;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value="/register",method = RequestMethod.POST)
    public APIResult register(@RequestBody User user){
        System.out.println(user);
        APIResult apiResult=loginService.register(user);
        return apiResult;
    }

    /**
     * 检查用户名是否重复
     * @param name
     * @return
     */
    @RequestMapping(value = "/nameCheck/{name}",method = RequestMethod.GET)
    public APIResult nameCheck(@PathVariable("name") String name) {
        APIResult apiResult = loginService.nameCheck(name);
        return apiResult;
    }

    /**
     * 生成验证码
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value="/signCode")
    public APIResult signCode(HttpSession session, HttpServletResponse response) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String code = SignCodeFactory.create(60, 30, "jpg", outputStream);
        SignCode signCode = new SignCode();
        signCode.setCode(code);
        signCode.setLifeTime((long) (60 * 1000));
        signCode.setCreateTime(System.currentTimeMillis());
        session.setAttribute("signCode", signCode);
        try {
            ServletOutputStream out = response.getOutputStream();
            outputStream.writeTo(out);
            return APIResult.createOKMessage("获取验证码成功");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return APIResult.createNg("获取图片失败");
        }
    }

    /**
     * 校验验证码
     * @param session
     * @param signCode
     * @return
     */
    @RequestMapping(value="/verifySignCode/{signCode}",method = RequestMethod.GET)
    public APIResult checkSignCode(HttpSession session, @PathVariable("signCode") String signCode) {
        // 当前时间戳
        long l = System.currentTimeMillis();
        SignCode sCode = (SignCode) session.getAttribute("signCode");
        if (l - sCode.getCreateTime() < sCode.getLifeTime()) {
            if (signCode.equalsIgnoreCase(sCode.getCode()) && signCode != null && signCode != "") {

                return APIResult.createOKMessage("验证码正确！");
            } else {

                return APIResult.createNg("验证码错误！");
            }
        } else {

            return APIResult.createNg("验证码失效！");
        }
    }

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello");
        return "hello";
    }

}
