package com.project.bountymission.exception;

// 验证码异常
public class VerificationCodeException extends BusinessException {
    public VerificationCodeException() {
        super("验证码错误");
    }
}