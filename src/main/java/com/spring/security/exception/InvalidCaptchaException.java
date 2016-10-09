package com.spring.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-08 16:14:00
 * 描    述：
 */
public class InvalidCaptchaException extends AuthenticationException {

    public InvalidCaptchaException() {
        super("您填写的验证码不正确!");
    }

    public InvalidCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }
}
