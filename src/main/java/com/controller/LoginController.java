package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.Authentication;
import com.annotation.UserInfo;
import com.annotation.UserInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 8:00 PM
 */
@RestController
public class LoginController {

    @ResponseBody
    @PostMapping(value = "/login-auth")
    @Authentication
    public Object loginAuth(HttpServletRequest req, HttpServletResponse response) throws IOException {
        UserInfo userInfo = UserInfoService.getUserInfo();
        if (userInfo != null) {
            String user = JSONObject.toJSONString(userInfo);
            Cookie cookie = new Cookie("token", user);
            cookie.setMaxAge(3600);

            response.addCookie(cookie);

        }
        return HttpServletResponse.SC_OK;
    }

    @ResponseBody
    @PostMapping(value = "/login")
    public Object login(HttpServletRequest req, HttpServletResponse response) throws IOException {
        UserInfo userInfo = UserInfoService.getUserInfo();
        if (userInfo != null) {
            String user = JSONObject.toJSONString(userInfo);
            Cookie cookie = new Cookie("token", user);
            cookie.setMaxAge(3600);

            response.addCookie(cookie);

        }
        return HttpServletResponse.SC_OK;
    }

}
