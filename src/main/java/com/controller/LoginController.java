package com.controller;

import com.annotation.Authentication;
import com.entity.UserInfo;
import com.entity.UserInfoHolder;
import com.input.LoginInput;
import com.jwt.JwtTokenGenerator;
import com.output.BaseOutput;
import com.output.ResponseFactory;
import com.output.UserOutput;
import com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/login")
    public void loginAuth(@RequestBody LoginInput loginInput, HttpServletRequest req, HttpServletResponse response) throws IOException {

        String message = "登录成功";
        int code = HttpServletResponse.SC_OK;

        UserInfo user = userInfoService.login(loginInput.getAccount(), loginInput.getPassword());
        if (user != null) {
            String token = JwtTokenGenerator.encode(user);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        } else {
            code = HttpServletResponse.SC_UNAUTHORIZED;
            message = "用户名或密码错误";
            response.addCookie(new Cookie("token", ""));
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        BaseOutput output = new BaseOutput(code, message);
        writer.write(output.toJson());
        writer.close();
    }

    @ResponseBody
    @GetMapping(value = "/get-user")
    @Authentication
    public BaseOutput getUser() {
        UserInfo userInfo = UserInfoHolder.getUserInfo();
        if (userInfo == null) {
            return ResponseFactory.get(500, "未找到该用户信息");
        }
        return ResponseFactory.get(new UserOutput(userInfo));
    }
}
