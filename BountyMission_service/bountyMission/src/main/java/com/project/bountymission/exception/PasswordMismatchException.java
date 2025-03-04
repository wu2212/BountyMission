package com.project.bountymission.exception;

// 密码错误异常
public class PasswordMismatchException extends BusinessException {
    public PasswordMismatchException() {
        super(401, "密码错误");
    }
}
