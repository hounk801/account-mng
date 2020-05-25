package com.hnk.login.entity;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/2 10:52 AM
 */
public class UserInfoHolder {
    private static final ThreadLocal<UserInfo> context = new ThreadLocal<>();

    public static UserInfo getUserInfo() {
        return context.get();
    }

    public static void setUserInfo(UserInfo userInfo) {
        context.set(userInfo);
    }

    public static void clear() {
        context.remove();
    }
}
