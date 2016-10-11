package com.spring.security.controller;

import com.spring.security.controller.security.DefaultAppController;
import com.spring.security.security.WebUserDetails;
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
    @GetMapping(value = {"/home/{url}"})
    public ModelAndView home(@PathVariable(required = false) String url) {
        WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView model = new ModelAndView();
        model.addObject("appMenus", webUserDetails.getAppMenus());
        model.addObject("url", "/" + url);
        model.setViewName("public/public-home");
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

}
