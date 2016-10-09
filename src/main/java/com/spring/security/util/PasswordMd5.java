package com.spring.security.util;

import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-09 10:58:00
 * 描    述：密码加密
 */
public class PasswordMd5 {

    public static String standPwdEncoder(String rawStr){
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("my-secret-key");
        return encoder.encode(rawStr);
    }
}
