package com.hnk.login.annotation;

import com.hnk.login.entity.UserInfoHolder;
import com.hnk.login.jwt.JwtTokenGenerator;
import com.hnk.login.output.BaseOutput;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 08:56 AM
 */
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
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }

            if (token != null && token.length() > 0) {
                boolean islogin = JwtTokenGenerator.decode(token);
                if (islogin) {
                    return true;
                }
            }

            int code = HttpServletResponse.SC_UNAUTHORIZED;
            String message = "登录超时或用户名密码错误，请重新登录";
            BaseOutput output = new BaseOutput(code, message);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
            writer.write(output.toJson());
            writer.close();
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoHolder.clear();
    }
}
