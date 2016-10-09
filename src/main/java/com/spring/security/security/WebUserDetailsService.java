package com.spring.security.security;


import com.spring.security.domain.master.AppMenu;
import com.spring.security.domain.master.AppRole;
import com.spring.security.domain.master.AppUser;
import com.spring.security.domain.master.AppUserExample;
import com.spring.security.mapper.master.AppUserMapper;
import com.spring.security.mapper.self.RoleAndMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-30 10:39:00
 * 描    述：实现 UserDetailsService 接口，主要是在 loadUserByUsername 方法中验证一个用户
 * 这里需要从数据库中读取验证表单提交过来的用户
 */
public class WebUserDetailsService implements UserDetailsService {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Autowired
    private AppUserMapper userMapper;

    @Autowired
    private RoleAndMenuMapper roleAndMenuMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //该方法负责实现验证并授权
        AppUserExample userExample = new AppUserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<AppUser> users = userMapper.selectByExample(userExample);

        if (0 == users.size()) {
            throw new BadCredentialsException(messages.getMessage("用户不存在！", new Object[]{username}, "账户 {0} 不存在"));
        }

        AppUser user = users.get(0);

        if (user.getStatus().equals(0) || user.getStatus() == 0) {
            throw new UsernameNotFoundException(messages.getMessage("账户已经禁用！", new Object[]{username}, "账户 {0} 已经禁用"));
        }

        //获取到当前用户所有角色给user
        user.setRoles(roleAndMenuMapper.getAppRolesByUserId(user.getId(), 1));
        if (null == user.getRoles()) {
            throw new UsernameNotFoundException(messages.getMessage("账户没有权限！", new Object[]{username}, "账户 {0} 失效"));
        }

        //读取当前用户有哪些角色
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Map<Integer, AppMenu> appMenus = new HashMap<Integer, AppMenu>();//通过ID去重
        for (AppRole userRole : user.getRoles()) {
            //这里的 role 参数为自己定义的，要和 SecurityMetadataSource 中的 SecurityConfig 参数对应
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());
            authorities.add(authority);

            appMenus.putAll(this.getMenusByRoleName(userRole.getId()));
        }

        Set<AppMenu> menus = new TreeSet<AppMenu>(new ArrayList<AppMenu>());
        menus.addAll(appMenus.values());

        //创建 UserDetails 对象
        return new WebUserDetails(user.getId(), username, user.getPassword(), true, authorities, menus);
    }

    /**
     * 获取角色的所有权限
     *
     * @param roleId    角色ID
     * @return          权限的集合
     */
    public Map<Integer, AppMenu> getMenusByRoleName (Integer roleId){

        Set<AppMenu> appMenus = new TreeSet<AppMenu>(new ArrayList<AppMenu>());
        appMenus.addAll(roleAndMenuMapper.getAppMenusByRoleId(roleId, 1));

        Map<Integer, AppMenu> map = new HashMap<Integer, AppMenu>();
        if (appMenus.size() > 0) {
            for (AppMenu appMenu : appMenus) {
                if (appMenu.getParentid().equals(0)) {
                    map.put(appMenu.getId(), appMenu);
                } else {
                    AppMenu mapMenu = map.get(appMenu.getParentid());
                    Set<AppMenu> menus = mapMenu.getAppMenus();
                    menus.add(appMenu);
                    mapMenu.setAppMenus(menus);
                }
            }
        }
        return map;
    }

}