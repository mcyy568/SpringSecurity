package com.spring.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.security.exception.InvalidCaptchaException;
import com.spring.security.service.UserService;
import com.spring.security.util.CaptchaUtils;
import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-29 10:26:00
 * 描    述：登录Controller
 */
@Controller
public class LoginController extends DefaultAppController {

    @Autowired
    private UserService userService;

    /**
     * 进入登录页
     *
     * @param error
     * @param logout
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public ModelAndView login(String error, String logout) {
        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }
        if (logout != null) {
            model.addObject("msg", "您已成功注销。");
        }
        model.setViewName("login");
        return model;
    }


    @PostMapping(value = "register")
    @ResponseBody
    public JSONObject register(@RequestBody JSONObject object) throws Exception {

        if (CaptchaUtils.verify(request, object.getString("captcha"))) {
            throw new InvalidCaptchaException();
        }

        userService.saveUser(object);

        return successDataToJSONString();
    }

    /**
     * 获取验证码
     */
    @GetMapping("/captcha")
    public void getCaptcha(Model model, HttpServletRequest request, HttpServletResponse response) {

        List<Color> colors = new ArrayList<Color>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.RED);

        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("Geneva", 2, 32));
        fonts.add(new Font("Courier", 3, 32));
        fonts.add(new Font("Arial", 1, 32));

        //WordRenderer wordRenderer = new ColoredEdgesWordRenderer(colors, fonts);
        WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);

//        Captcha captcha = new Captcha.Builder(150, 50).addText(wordRenderer).gimp(new DropShadowGimpyRenderer())
//                .addBackground(new TransparentBackgroundProducer()).build();
        Captcha captcha = new Captcha.Builder(85, 33).addText(wordRenderer).build();

        request.getSession().setAttribute(Captcha.NAME, captcha.getAnswer());
        CaptchaServletUtil.writeImage(response, captcha.getImage());

    }

    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String error = null;
        if (null != exception) {
            if (exception instanceof InvalidCaptchaException) {//验证码不正确
                error = exception.getMessage();
            } else if (exception instanceof BadCredentialsException) {//密码错误
                error = "密码错误！";
            } else if (exception.getCause() instanceof BadCredentialsException) {//账户不存在
                error = exception.getMessage();
            } else if (exception instanceof LockedException) {//账户是否锁定//深度控制可以使用
                error = exception.getMessage();
            } else if (exception instanceof UsernameNotFoundException) {//账户已经禁用
                error = exception.getMessage();
            }
        }
        return error;
    }

}
