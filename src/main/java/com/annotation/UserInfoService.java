package com.annotation;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 7:46 PM
 */
public class UserInfoService {

    private static final ThreadLocal<UserInfo> context = new ThreadLocal<>();

    public static boolean authNameAndPasswd(String name, String passwd) {
        boolean auth = false;
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


    public static boolean authNameAndPasswd(String token) throws UnsupportedEncodingException {

        boolean auth = false;

        if (StringUtils.isEmpty(token)) {
            return auth;
        }
        String user = URLDecoder.decode(token, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(user);
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
