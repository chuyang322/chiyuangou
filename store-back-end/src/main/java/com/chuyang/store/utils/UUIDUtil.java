package com.chuyang.store.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtil
 * @Description TODO:
 * @Author chuyang
 * @Date 2022/7/5 15:09
 * @Version 1.0
 **/
public class UUIDUtil {

    /**
     * 获取UUID字符串
     * @return UUID字符串
     */
    public static String getUUIDString(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
