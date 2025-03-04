package com.project.bountymission.pojo;



import java.util.HashMap;
import java.util.Map;

/**
 * 通用响应类
 */
public class Result<T> {
    // 响应状态码
    private int code;
    // 响应消息
    private String message;
    // 响应数据
    private T data;

    // 默认成功状态码
    public static final int SUCCESS_CODE = 200;
    // 默认失败状态码
    public static final int FAIL_CODE = 400;

    // 私有构造函数
    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应的工厂方法
    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, "操作成功", data);
    }

    // 成功响应的工厂方法（无数据）
    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "操作成功", null);
    }

    // 失败响应的工厂方法
    public static <T> Result<T> error(String message) {
        return new Result<>(FAIL_CODE, message, null);
    }
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(FAIL_CODE, message, null);
    }

    // 自定义状态码和消息的响应
    public static <T> Result<T> custom(int code, String message, T data) {
        return new Result<>(code, message, data);
    }

    // 获取响应数据
    public T getData() {
        return data;
    }

    // 获取状态码
    public int getCode() {
        return code;
    }

    // 获取消息
    public String getMessage() {
        return message;
    }

    // 转换为 Map（方便 JSON 序列化）
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        return map;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
