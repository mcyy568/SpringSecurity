package com.spring.security.security.logout;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-29 10:21:00
 * 描    述：退出成功处理一些逻辑
 */
public class CustomLogoutHandler implements LogoutHandler {

    public CustomLogoutHandler() {
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        System.out.println("退出成功处理一些逻辑");

    }

}

