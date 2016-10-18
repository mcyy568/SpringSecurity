package com.spring.security.controller;

import com.spring.security.controller.security.DefaultAppController;
import com.spring.security.security.WebUserDetails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-03 18:11:00
 * 描    述：
 */
@Controller
public class HomePageController extends DefaultAppController {

    /**
     * 登录转跳页
     *
     * @return
     */
    @GetMapping(value = {"/home/{url_1}"})
    public ModelAndView home1(@PathVariable(required = false) String url_1) {
        ModelAndView model = getWebUserDetails();
        model.addObject("url", "/" + url_1);
        return model;
    }

    @GetMapping(value = {"/home/{url_1}/{url_2}"})
    public ModelAndView home2(@PathVariable(required = false) String url_1,
                              @PathVariable(required = false) String url_2) {
        ModelAndView model = getWebUserDetails();
        model.addObject("url", "/" + url_1 + "/" + url_2);
        return model;
    }

    @GetMapping(value = {"/home/{url_1}/{url_2}/{url_3}"})
    public ModelAndView home3(@PathVariable(required = false) String url_1,
                              @PathVariable(required = false) String url_2,
                              @PathVariable(required = false) String url_3) {
        ModelAndView model = getWebUserDetails();
        model.addObject("url", "/" + url_1 + "/" + url_2 + "/" + url_3);
        return model;
    }

    @GetMapping(value = {"/home/{url_1}/{url_2}/{url_3}/{url_4}"})
    public ModelAndView home4(@PathVariable(required = false) String url_1,
                              @PathVariable(required = false) String url_2,
                              @PathVariable(required = false) String url_3,
                              @PathVariable(required = false) String url_4) {
        ModelAndView model = getWebUserDetails();
        model.addObject("url", "/" + url_1 + "/" + url_2 + "/" + url_3 + "/" + url_4);
        return model;
    }

    /**
     * 首页
     *
     * @return
     */
    @GetMapping(value = {"/homePage"})
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("item/home");
        return model;
    }

    public static ModelAndView getWebUserDetails() {
        WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView model = new ModelAndView();
        model.addObject("appMenus", webUserDetails.getAppMenus());
        model.addObject("title", "首页");
        model.setViewName("public/public-home");
        return model;
    }
}
