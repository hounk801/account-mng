package com.hnk.login.output;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/2 1:00 AM
 */
public class ResponseFactory {
    private ResponseFactory() {
    }

    public static <T> BaseOutput<T> get(T data) {
        return new BaseOutput<>(data);
    }

    public static <T> BaseOutput<T> get(int code, String message) {
        return new BaseOutput<>(code, message);
    }
}
