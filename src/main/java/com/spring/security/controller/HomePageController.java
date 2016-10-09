package com.spring.security.controller;

import com.spring.security.security.WebUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-03 18:11:00
 * 描    述：
 */
@Controller
public class HomePageController {

    @GetMapping(value = {"/HomePage"})
    public ModelAndView homePage() {

        WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ModelAndView model = new ModelAndView();
        model.addObject("appMenus", webUserDetails.getAppMenus());
        model.setViewName("item/home");
        return model;

    }

    @GetMapping(value = {"/HomePage2"})
    public ModelAndView homePage2() {

        ModelAndView model = new ModelAndView();
        WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addObject("appMenus", webUserDetails.getAppMenus());
        model.setViewName("item/home2");
        return model;

    }

}
