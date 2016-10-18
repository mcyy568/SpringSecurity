package com.spring.security.exception;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-14 14:23:00
 * 描    述：
 */
public class RoleRepeatExecption extends AuthException {

    public RoleRepeatExecption() {
        super("角色重复注册", null);
    }

    public RoleRepeatExecption(Object data) {
        super("角色重复注册", data);
    }
}
