package com.annotation;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 模拟数据库
 * @Author naikuo
 * @Date 2020/3/1 7:38 PM
 */
public class Database {

    public static Map<String, String> userInfo = new ConcurrentHashMap<>();

    static {
        userInfo.put("anny", "12345");
        userInfo.put("liming", "23456");
    }




}
