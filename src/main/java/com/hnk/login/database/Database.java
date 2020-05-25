package com.database;


import com.hnk.login.entity.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 模拟数据库
 * @Author naikuo
 * @Date 2020/3/1 7:38 PM
 */
public class Database {

    public static Map<String, UserInfo> userInfo = new HashMap<>();

    static {
        userInfo.put("anny@qq.com", new UserInfo("1", "anny", "anny@qq.com", "anny"));
        userInfo.put("liming@qq.com", new UserInfo("2", "liming", "liming@qq.com", "liming"));
    }

}
