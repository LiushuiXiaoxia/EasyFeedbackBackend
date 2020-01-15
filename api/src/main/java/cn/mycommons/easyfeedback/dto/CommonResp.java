package cn.mycommons.easyfeedback.dto;

import lombok.Data;

@Data
public class CommonResp<T> {

    private int code;
    private T data;
    private String msg;

    public CommonResp() {
    }

    public CommonResp(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> CommonResp<T> success(T data) {
        return new CommonResp<>(200, data, null);
    }

    public static <T> CommonResp<T> clientError(String msg) {
        return new CommonResp<>(400, null, msg);
    }

    public static <T> CommonResp<T> serverError(String msg) {
        return new CommonResp<>(500, null, msg);
    }
}