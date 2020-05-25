package com.hnk.login.service;

import com.database.Database;
import com.hnk.login.entity.UserInfo;
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

    public UserInfo getUserInfo(String account) {
        return Database.userInfo.get(account);
    }
}
