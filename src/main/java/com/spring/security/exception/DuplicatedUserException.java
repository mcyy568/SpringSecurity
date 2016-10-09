package com.spring.security.exception;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-09 11:26:00
 * 描    述：
 */
public class DuplicatedUserException extends AuthException {
    public DuplicatedUserException() {
        super("重复注册", null);
    }

    public DuplicatedUserException(Object data) {
        super("重复注册", data);
    }
}
