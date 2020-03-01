package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.annotation.Authentication;
import com.annotation.UserInfo;
import com.annotation.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @Description
 * @Author naikuo
 * @Date 2020/3/1 8:00 PM
 */
@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public Object loginAuth(@RequestBody UserInfo userInfo, HttpServletRequest req, HttpServletResponse response) throws IOException {

        boolean auth = UserInfoService.authNameAndPasswd(userInfo.getName(), userInfo.getPassword());
        if (!auth) {
            return HttpServletResponse.SC_UNAUTHORIZED;
        }
        String user = JSONObject.toJSONString(userInfo);
        Cookie cookie = new Cookie("token", java.net.URLEncoder.encode(user, "UTF-8"));
        cookie.setMaxAge(3600);

        response.addCookie(cookie);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        writer.close();

        return HttpServletResponse.SC_OK;
    }

    @ResponseBody
    @GetMapping(value = "/get-user")
    @Authentication
    public Object getUser(HttpServletRequest req, HttpServletResponse response) {
        UserInfo userInfo = UserInfoService.getUserInfo();
        return userInfo;
    }


}
