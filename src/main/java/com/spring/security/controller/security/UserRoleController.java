package com.spring.security.controller.security;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.spring.security.domain.master.AppMenu;
import com.spring.security.exception.RoleRepeatExecption;
import com.spring.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 创建用户：杨辽
 * 创建时间：2016-10-10 10:54:00
 * 描    述：
 */
@RestController
@RequestMapping("/role")
public class UserRoleController extends DefaultAppController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/page")
    public ModelAndView role() {
        ModelAndView model = new ModelAndView();

        model.addObject("appRoles", roleService.getRoleList());

        model.setViewName("permission/role");
        return model;
    }

    /**
     * 获取添加角色的权限树
     *
     * @return JSONObject
     */
    @GetMapping(value = "/getMenuTree")
    public JSONObject getMenuTree() {
        return getTopNode(roleService.getMenuTree(), true);
    }

    /**
     * 获取角色的权限视图报表
     *
     * @param roleId 角色ID
     * @return JSONObject
     */
    @GetMapping(value = "/getRoleMenu/{roleId}")
    public JSONObject getRoleMenu(@PathVariable Integer roleId) {
        return getRoleMenu(roleService.getRoleMenu(roleId, true), true, true);
    }

    /**
     * 获取编辑角色的权限树
     *
     * @param roleId 角色ID
     * @return JSONObject
     */
    @GetMapping(value = "/getEditRoleMenu/{roleId}")
    public JSONObject getEditRoleMenu(@PathVariable Integer roleId) {
        Set<AppMenu> appMenus = roleService.getRoleMenu(roleId, false);
        return getTopNode(roleService.getMenuTree(), true, appMenus);
    }

    /**
     * 添加角色
     *
     * @param obj obj
     * @return JSONObject
     * @throws RoleRepeatExecption
     */
    @PostMapping(value = "/role")
    public JSONObject saveRole(@RequestBody JSONObject obj) throws RoleRepeatExecption {
        roleService.saveRole(obj);
        return successDataToJSONString();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     * @return JSONObject
     * @throws RoleRepeatExecption
     */
    @DeleteMapping(value = "/role/{roleId}")
    public JSONObject deleteRole(@PathVariable Integer roleId) throws RoleRepeatExecption {
        roleService.deleteRole(roleId);
        return successDataToJSONString();
    }

    /**
     * 编辑角色
     *
     * @param roleId 角色ID
     * @param obj    角色信息
     * @return JSONObject
     * @throws RoleRepeatExecption
     */
    @PutMapping(value = "/role/{roleId}")
    public JSONObject editRole(@RequestBody JSONObject obj, @PathVariable Integer roleId) throws RoleRepeatExecption {
        roleService.editRole(obj, roleId);
        return successDataToJSONString();
    }

    /*处理角色的权限报表*/
    public static JSONObject getRoleMenu(Set<AppMenu> appMenus, boolean ontAppMenus, boolean one) {
        JSONObject object = new JSONObject();
        JSONArray children_1 = new JSONArray();
        for (AppMenu appMenu : appMenus) {
            if (ontAppMenus) {
                object.put("name", appMenu.getMenuname());
                ontAppMenus = false;
                continue;
            }
            JSONObject topNode_1 = new JSONObject();
            topNode_1.put("name", appMenu.getMenuname());
            if (appMenu.getAppMenus().size() > 0) {
                JSONArray children_2 = new JSONArray();
                for (AppMenu appMenu_2 : appMenu.getAppMenus()) {
                    JSONObject topNode_2 = new JSONObject();
                    topNode_2.put("name", appMenu_2.getMenuname());
                    if (appMenu_2.getAppMenus().size() > 0) {
                        topNode_2.put("children", getRoleMenu(appMenu_2.getAppMenus(), false, true));
                    }
                    children_2.add(topNode_2);
                }
                topNode_1.put("children", children_2);
            }
            children_1.add(topNode_1);
        }
        if (one) {
            object.put("children", children_1);
        }
        return object;
    }


    /*处理权限树 编辑用户权限信息时传入*/
    public static JSONObject getTopNode(Set<AppMenu> appMenus, boolean getAppMenus, Set<AppMenu>... editAppMenus) {
        JSONObject object = new JSONObject();
        JSONObject topNode = new JSONObject();
        JSONObject additionalParameters = new JSONObject();
        TreeMap<Integer, JSONObject> children = new TreeMap<>((a, b) -> b - a);

        Integer i = 0;
        for (AppMenu appMenu : appMenus) {
            if (getAppMenus) {
                topNode.put("id", appMenu.getId());
                topNode.put("name", appMenu.getMenuname());
                topNode.put("type", "folder");
                getAppMenus = false;
            } else {
                JSONObject twoNode = new JSONObject();
                twoNode.put("id", appMenu.getId());
                twoNode.put("name", appMenu.getMenuname());
                twoNode.put("type", appMenu.getAppMenus().size() == 0 ? "item" : "folder");
                /*编辑角色用*/
                Set<AppMenu> temp0 = null;
                for (Set<AppMenu> temp : editAppMenus) {
                    if (null != temp) {
                        twoNode.put("defaultSelected", temp.stream().filter((a) -> a.getId().equals(appMenu.getId())).collect(Collectors.toList()).size() > 0);//默认选中
                        temp0 = temp;
                    }
                }
                if (appMenu.getAppMenus().size() > 0) {
                    twoNode.put("additionalParameters", getTopNode(appMenu.getAppMenus(), false, temp0).getJSONObject("topNode").get("additionalParameters"));
                }
                children.put(i, twoNode);
                additionalParameters.put("children", children);
                additionalParameters.put("children", children);
                topNode.put("additionalParameters", additionalParameters);
            }
            i++;
        }
        object.put("topNode", topNode);
        return object;
    }


}
