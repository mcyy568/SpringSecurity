package com.spring.security.service;

import com.alibaba.fastjson.JSONObject;
import com.spring.security.domain.master.AppMenu;
import com.spring.security.domain.master.AppRole;
import com.spring.security.exception.RoleRepeatExecption;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-11 15:21:00
 * 描    述：
 */
public interface RoleService {

    List<AppRole> getRoleList();

    Set<AppMenu> getMenuTree();

    /* assemble 判断是否组装数据 */
    Set<AppMenu> getRoleMenu(Integer roleId, boolean assemble);

    void saveRole(JSONObject object) throws RoleRepeatExecption;

    void editRole(JSONObject object, Integer roleId) throws RoleRepeatExecption;

    void deleteRole(Integer roleId) throws RoleRepeatExecption;


    public static Set<AppMenu> handleAppMenu(List<AppMenu> nums){
        Set<AppMenu> fatherAppMenu = new TreeSet<AppMenu>(new ArrayList<AppMenu>());
        fatherAppMenu.addAll(nums.stream().filter(student -> student.getParentid() == 0).collect(Collectors.toList()));

        for (AppMenu appMenu : fatherAppMenu) {
            Set<AppMenu> set = new TreeSet<AppMenu>(new ArrayList<AppMenu>());
            set.addAll(nums.stream().filter(student -> student.getParentid().equals(appMenu.getId())).collect(Collectors.toList()));
            appMenu.setAppMenus(set);
        }
        return fatherAppMenu;
    }
}
