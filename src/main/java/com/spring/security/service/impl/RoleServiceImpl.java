package com.spring.security.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.security.domain.master.*;
import com.spring.security.exception.RoleRepeatExecption;
import com.spring.security.mapper.master.AppMenuMapper;
import com.spring.security.mapper.master.AppRoleMapper;
import com.spring.security.mapper.master.AppRoleMenuMapper;
import com.spring.security.mapper.self.RoleAndMenuMapper;
import com.spring.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-11 15:21:00
 * 描    述：
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private AppRoleMapper appRoleMapper;
    @Autowired
    private AppRoleMenuMapper appRoleMenuMapper;
    @Autowired
    private RoleAndMenuMapper roleAndMenuMapper;
    @Autowired
    private AppMenuMapper appMenuMapper;

    @Override
    public List<AppRole> getRoleList() {
        AppRoleExample example = new AppRoleExample();
        return appRoleMapper.selectByExample(example);
    }

    @Override
    public Set<AppMenu> getMenuTree() {
        AppMenuExample example = new AppMenuExample();
        List<AppMenu> nums = appMenuMapper.selectByExample(example);
        return RoleService.handleAppMenu(nums);
    }

    @Override
    public Set<AppMenu> getRoleMenu(Integer roleId, boolean assemble) {
        if (assemble) {
            List<AppMenu> nums = new ArrayList<>();
            nums.addAll(roleAndMenuMapper.getAppMenusByRoleId(roleId, null));
            return RoleService.handleAppMenu(nums);
        } else {
            return roleAndMenuMapper.getAppMenusByRoleId(roleId, null);
        }
    }

    @Override
    public void saveRole(JSONObject object) throws RoleRepeatExecption {

        AppRoleExample example = new AppRoleExample();
        example.createCriteria().andNameEqualTo("ROLE_" + object.getString("rolename").toUpperCase());
        if (appRoleMapper.selectByExample(example).size() > 0) {
            throw new RoleRepeatExecption();
        }
        Date date = new Date();

        AppRole appRole = new AppRole();
        appRole.setName("ROLE_" + object.getString("rolename").toUpperCase());
        appRole.setCreatetime(date);
        appRole.setLastupdate(date);
        appRole.setStatus(object.getBoolean("checkbox") ? 1 : 0);
        appRole.setDescn(object.getString("roledescn"));
        appRoleMapper.insertSelective(appRole);

        /*添加权限*/
        insertAppRoleMenu(appRoleMenuMapper, object.getJSONArray("tree"), appRole.getId());
    }

    @Override
    public void editRole(JSONObject object, Integer roleId) throws RoleRepeatExecption {
        AppRoleExample appRoleMapperExample = new AppRoleExample();
        appRoleMapperExample.createCriteria().andNameEqualTo(object.getString("rolename").toUpperCase());
        if (appRoleMapper.selectByExample(appRoleMapperExample).size() != 1) {
            throw new RoleRepeatExecption();
        }

        Date date = new Date();
        AppRole appRole = new AppRole();
        appRole.setId(roleId);
        appRole.setName(object.getString("rolename").toUpperCase());
        appRole.setLastupdate(date);
        appRole.setStatus(object.getInteger("checkbox"));
        appRole.setDescn(object.getString("roledescn"));
        appRoleMapper.updateByPrimaryKeySelective(appRole);

        /*删除所有权限*/
        deleteRoleMenu(appRoleMenuMapper, roleId);

        /*添加权限*/
        insertAppRoleMenu(appRoleMenuMapper, object.getJSONArray("tree"), roleId);
    }

    @Override
    public void deleteRole(Integer roleId) throws RoleRepeatExecption {
        appRoleMapper.deleteByPrimaryKey(roleId);
        deleteRoleMenu(appRoleMenuMapper, roleId);
    }

    public static void deleteRoleMenu(AppRoleMenuMapper appRoleMenuMapper, Integer roleId){
        AppRoleMenuExample appRoleMenuMapperExample = new AppRoleMenuExample();
        appRoleMenuMapperExample.createCriteria().andRoleidEqualTo(roleId);
        appRoleMenuMapper.deleteByExample(appRoleMenuMapperExample);
    }

    public static void insertAppRoleMenu(AppRoleMenuMapper appRoleMenuMapper, JSONArray jsonArray, Integer roleId){
        AppRoleMenu roleMenu = new AppRoleMenu();
        roleMenu.setRoleid(roleId);

        Set<Integer> integers = new HashSet<>();
        integers.add(1);
        for (Object o : jsonArray) {//权限ID去重
            integers.add(Integer.valueOf(o.toString()));
        }
        for (Integer integer : integers) {
            roleMenu.setMenuid(integer);
            appRoleMenuMapper.insertSelective(roleMenu);
        }
    }
}
