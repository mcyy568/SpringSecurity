package com.spring.security.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-29 15:53:00
 * 描    述：记录失败次数，判断是否显示验证码
 */
public class SimpleUrlAuthenticationFailureHandler extends org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler {
    public static String SESSION_FAIL_TIMES = "SPRING_SESSION_FAIL_TIMES";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Object attribute = session.getAttribute(SESSION_FAIL_TIMES);
            Integer times;
            if (attribute != null) {
                times = (Integer) attribute;
                times++;
            } else {
                times = 1;
            }
            request.getSession().setAttribute(SESSION_FAIL_TIMES, times);
        }
        super.onAuthenticationFailure(request, response, exception);
    }

}