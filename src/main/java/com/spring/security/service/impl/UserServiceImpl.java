package com.spring.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.spring.security.domain.master.AppUser;
import com.spring.security.domain.master.AppUserExample;
import com.spring.security.domain.master.AppUserRole;
import com.spring.security.exception.AuthException;
import com.spring.security.exception.DuplicatedUserException;
import com.spring.security.exception.InvalidCaptchaException;
import com.spring.security.mapper.master.AppUserMapper;
import com.spring.security.mapper.master.AppUserRoleMapper;
import com.spring.security.service.UserService;
import com.spring.security.util.PasswordMd5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-27 14:45:00
 * 描    述：
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    protected final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private AppUserMapper userMapper;
    @Autowired
    private AppUserRoleMapper appUserRoleMapper;

    public List<AppUser> getUserList() {
        AppUserExample example = new AppUserExample();
        return userMapper.selectByExample(example);
    }

    @Override
    public AppUser getUserByUserName(String userName) throws Exception {
        AppUserExample example = new AppUserExample();
        example.createCriteria().andUsernameEqualTo(userName);
        List<AppUser> users = userMapper.selectByExample(example);
        if (null == users){
            throw new Exception("用户不存在！");
        }
        return users.get(0);
    }

    @Override
    public Integer saveUser(JSONObject object) throws DuplicatedUserException {
        Date date = new Date();
        AppUser appUser = JSONObject.parseObject(object.toJSONString(), AppUser.class);

        AppUserExample example = new AppUserExample();
        example.createCriteria().andUsernameEqualTo(appUser.getUsername());
        List<AppUser> appUsers = userMapper.selectByExample(example);
        if (null != appUsers && appUsers.size() > 0) {
            throw new DuplicatedUserException();
        }

        appUser.setPassword(PasswordMd5.standPwdEncoder(appUser.getPassword()));
        appUser.setCreatetime(date);
        appUser.setLastupdate(date);
        appUser.setStatus(1);//用户状态1可用
        userMapper.insertSelective(appUser);

        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setRoleid(2);//普通用户角色
        appUserRole.setUserid(appUser.getId());
        appUserRoleMapper.insertSelective(appUserRole);

        return null;
    }
}
