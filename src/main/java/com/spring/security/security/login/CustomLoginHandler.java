package com.spring.security.security.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-29 10:18:00
 * 描    述：自定义登录成功后的处理handler
 */
public class CustomLoginHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

        //TODO 这里可以追加开发人员自己的额外处理
        System.out.println("这里可以追加开发人员自己的额外处理");

        HttpSession session = request.getSession(false);
        if (session != null ) {
            request.getSession().setAttribute(SimpleUrlAuthenticationFailureHandler.SESSION_FAIL_TIMES, 0);
        }
        super.onAuthenticationSuccess(request, response, authentication);

    }

}
