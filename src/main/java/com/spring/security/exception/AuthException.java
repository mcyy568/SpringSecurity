package com.spring.security.exception;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-08 15:53:00
 * 描    述：异常处理类
 */
public class AuthException extends Exception {
    protected final Object data;

    public AuthException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
