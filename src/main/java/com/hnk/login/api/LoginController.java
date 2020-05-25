package com.hnk.login.api;

import com.hnk.login.annotation.Authentication;
import com.hnk.login.entity.UserInfo;
import com.hnk.login.entity.UserInfoHolder;
import com.hnk.login.input.LoginInput;
import com.hnk.login.jwt.JwtTokenGenerator;
import com.hnk.login.output.BaseOutput;
import com.hnk.login.output.ResponseFactory;
import com.hnk.login.output.UserOutput;
import com.hnk.login.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/login")
    public void loginAuth(@RequestBody LoginInput loginInput, HttpServletResponse response) throws IOException {

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

            LOGGER.info("login fail userName={}", loginInput.getAccount());
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
            return ResponseFactory.get(HttpServletResponse.SC_UNAUTHORIZED, "未找到该用户信息");
        }
        return ResponseFactory.get(new UserOutput(userInfo));
    }
}
