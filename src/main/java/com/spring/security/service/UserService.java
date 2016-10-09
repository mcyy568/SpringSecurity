package com.spring.security.service;

import com.alibaba.fastjson.JSONObject;
import com.spring.security.domain.master.AppUser;
import com.spring.security.exception.DuplicatedUserException;

import java.util.List;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-27 14:45:00
 * 描    述：
 */
public interface UserService {

    List<AppUser> getUserList();

    AppUser getUserByUserName(String userName) throws Exception;

    Integer saveUser(JSONObject object) throws Exception ;

}
