package com.spring.security.controller.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-10 10:54:00
 * 描    述：
 */
@RestController
public class UserRoleController extends DefaultAppController {

    @GetMapping(value = "/role")
    public ModelAndView role(){
        ModelAndView model = new ModelAndView();
        model.setViewName("permission/role");
        return model;
    }

}
