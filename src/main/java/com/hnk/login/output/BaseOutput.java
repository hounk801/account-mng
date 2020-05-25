package com.hnk.login.output;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description TODO
 * @Author naikuo
 * @Date 2020/3/2 12:56 AM
 */
@Setter
@Getter
@ToString
public class BaseOutput<T> {
    private int code = HttpServletResponse.SC_OK;
    private String message = "ok";
    private T data;

    public BaseOutput(T data) {
        this.data = data;
    }

    public BaseOutput(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }
}
