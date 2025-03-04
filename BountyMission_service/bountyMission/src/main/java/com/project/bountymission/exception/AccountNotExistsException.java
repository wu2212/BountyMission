package com.project.bountymission.exception;

// 账号不存在异常
public class AccountNotExistsException extends BusinessException {
    public AccountNotExistsException() {
        super(404, "账号不存在,请先注册");
    }
}
