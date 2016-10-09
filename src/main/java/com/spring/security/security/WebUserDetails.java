package com.spring.security.security;

import java.util.Collection;
import java.util.Set;

import com.spring.security.domain.master.AppMenu;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-30 10:50:00
 * 描    述：该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 获得对象的方式：WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 如果需要包括用户的其他属性，可以在该类中增加相应属性即可
 */
public class WebUserDetails implements UserDetails {

    private static final long serialVersionUID = -8242940190960961504L;

    private String username;
    private String password;
    private boolean userEnabled;
    private Collection<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    //额外增加的属性
    private Integer userId;
    private Set<AppMenu> appMenus; //当前用户的权限集合

    /**
     * 储存所有用户信息
     *
     * @param userId        用户ID
     * @param username      用户账户
     * @param password      用户密码
     * @param userEnabled   用户启用
     * @param authorities   储存用户所有角色
     * @param appMenus      储存用户所有权限
     */
    public WebUserDetails(Integer userId, String username, String password, boolean userEnabled,
                          Collection<GrantedAuthority> authorities, Set<AppMenu> appMenus) {
        this.username = username;
        this.password = password;
        this.userEnabled = userEnabled;//用户启用
        this.authorities = authorities;

        //这里先初始都为true，如果需要深度控制，可完善
        this.accountNonExpired = true;//账户未过期
        this.accountNonLocked = true;//帐户不锁定
        this.credentialsNonExpired = true;//凭据非过期

        //用户ID
        this.userId = userId;
        this.appMenus = appMenus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.userEnabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public Set<AppMenu> getAppMenus() {
        return appMenus;
    }
}

