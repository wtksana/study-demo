package com.tt.study.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * 响应数据类
 * date: 2018/3/15
 * author: wt
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     * {@link   RespCode}
     */
    @Getter
    private int code;

    /**
     * 响应信息
     */
    @Getter
    private String msg;

    /**
     * 数据
     */
    @Getter
    private T data;

    private RespInfo() {
    }

    private RespInfo(int code) {
        this.code = code;
    }

    private RespInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private RespInfo(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private RespInfo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.code == RespCode.SUCCESS.getCode();
    }

    public static <T> RespInfo<T> success() {
        return new RespInfo<>(RespCode.SUCCESS.getCode());
    }

    public static <T> RespInfo<T> success(String msg) {
        return new RespInfo<>(RespCode.SUCCESS.getCode(), msg);
    }

    public static <T> RespInfo<T> success(T data) {
        return new RespInfo<>(RespCode.SUCCESS.getCode(), data);
    }

    public static <T> RespInfo<T> success(String msg, T data) {
        return new RespInfo<>(RespCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> RespInfo<T> error() {
        return new RespInfo<>(RespCode.ERROR.getCode());
    }

    public static <T> RespInfo<T> error(String msg) {
        return new RespInfo<>(RespCode.ERROR.getCode(), msg);
    }

    public static <T> RespInfo<T> error(int code, String msg) {
        return new RespInfo<>(code, msg);
    }
}
