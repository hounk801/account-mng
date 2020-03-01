package com.service;

import com.entity.UserInfo;
import com.database.Database;
import com.security.LoginToken;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/1 7:46 PM
 */
@Service
public class UserInfoService {

    public UserInfo login(String account, String passwd) {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(passwd)) {
            return null;
        }

        if (Database.userInfo.containsKey(account)) {
            UserInfo userInfo = Database.userInfo.get(account);
            if (userInfo != null && passwd.equals(userInfo.getPassword())) {
                return userInfo;
            }
        }
        return null;
    }

    public boolean authenticate(String account, String passwd) {

        // Use Shiro to pass through a username password token.
        LoginToken token = new LoginToken(account, passwd);
        Subject subject = SecurityUtils.getSubject();

        subject.login(token);
        return true;
    }

    public UserInfo getUserInfo(String account) {
        return Database.userInfo.get(account);
    }
}
