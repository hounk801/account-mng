package com.hnk.account.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 接口返回对象
 * @author 懒猴子CG
 * @date 2020/05/25 16:04
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private Boolean success;

    private String message;

    private T data;

    /**
     * 请求成功
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    public static <T> ApiResponse success(T data) {
        return ApiResponse.success("请求成功", data);
    }

    /**
     * 请求成功
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    public static <T> ApiResponse success(String message, T data) {
        return new ApiResponse(Boolean.TRUE, message, data);
    }

    /**
     * 请求失败
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    public static ApiResponse failed() {
        return ApiResponse.failed("请求失败");
    }

    /**
     * 请求失败
     * @author 懒猴子CG
     * @date 2020/05/25 16:04
     */
    public static ApiResponse failed(String message) {
        return new ApiResponse(Boolean.FALSE, message, null);
    }
}