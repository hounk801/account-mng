package com.annotation;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 7:46 PM
 */
public class UserInfoService {

    private static final ThreadLocal<UserInfo> context = new ThreadLocal<>();

    public static boolean authNameAndPasswd(String token) {

        boolean auth = false;

        if (StringUtils.isEmpty(token)) {
            return auth;
        }
        JSONObject jsonObject = JSONObject.parseObject(token);
        String name = jsonObject.getString("name");
        String passwd = jsonObject.getString("password");

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(passwd)) {
            return auth;
        }

        if (Database.userInfo.containsKey(name)) {
            if (passwd.equals(Database.userInfo.get(name))) {
                auth = true;
                context.set(new UserInfo(name, passwd));
            }
        }
        return auth;
    }

    public static UserInfo getUserInfo() {
        return context.get();
    }

}
