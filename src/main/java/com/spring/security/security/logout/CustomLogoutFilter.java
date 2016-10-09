package com.spring.security.security.logout;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-29 10:15:00
 * 描    述：注销filter
 */
public class CustomLogoutFilter extends LogoutFilter {

    public CustomLogoutFilter(String logoutSuccessUrl, LogoutHandler[] handlers) {
        super(logoutSuccessUrl, handlers);
    }

    public CustomLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler[] handlers) {
        super(logoutSuccessHandler, handlers);
    }

}
