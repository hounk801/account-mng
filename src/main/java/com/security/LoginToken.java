package com.security;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 10:47 PM
 */
@Data
@AllArgsConstructor
public class LoginToken implements AuthenticationToken {
    private String account;
    private String password;

    public String toJson() {
        return JSONObject.toJSONString(this);
    }

    @Override
    public Object getPrincipal() {
        return account;
    }

    @Override
    public Object getCredentials() {
        return password;
    }
}
