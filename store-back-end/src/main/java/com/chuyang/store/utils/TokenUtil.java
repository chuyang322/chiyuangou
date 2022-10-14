package com.chuyang.store.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.chuyang.store.model.User;

import java.util.*;

/**
 * @ClassName TokenUtil
 * @Description TODO: token工具类
 * @Author chuyang
 * @Date 2022/5/21 10:47
 * @Version 1.0
 **/
public class TokenUtil {

    // 过期时间为3分钟
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    // 秘钥
    private static final String TOKEN_SECRET = UUID.randomUUID().toString();
    /**
     * 生成token
     *
     * @param id
     * @param name
     * @return
     */
    public static String getToken(Integer id,String name) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        System.out.println(TOKEN_SECRET);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        return JWT.create()
                .withClaim("userId", id)
                .withClaim("username", name)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token) {
        try {
            System.out.println(TOKEN_SECRET);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
        } catch (IllegalArgumentException e) {
            return false;
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }
}
