package com.annotation;

import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(Authentication.class)) {

            Cookie[] cookies = request.getCookies();
            String token = "";
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "token":
                        token = cookie.getValue();
                        break;
                    default:
                        break;
                }
            }

            if (token != null && token.length() > 0) {
                boolean islogin = UserInfoService.authNameAndPasswd(token);
                if (islogin) {
                    return true;
                }
            }

            response.setStatus(401);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.write("请登录");
            writer.close();
            return false;
        }
        return true;
    }
}
