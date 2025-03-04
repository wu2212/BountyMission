package com.project.bountymission.exception;

// 账号已存在异常
public class AccountExistsException extends BusinessException {
    public AccountExistsException() {
        super(409, "账号已存在");
    }
}