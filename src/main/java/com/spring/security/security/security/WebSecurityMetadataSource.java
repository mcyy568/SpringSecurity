package com.spring.security.security.security;


import com.spring.security.domain.master.AppMenu;
import com.spring.security.domain.master.AppRole;
import com.spring.security.mapper.self.RoleAndMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 创建用户：杨辽
 * 创建时间：2016-09-30 15:38:00
 * 描    述：核心处理逻辑 启动项目获取项目中所有的角色与权限，如果数据库中的角色或权限改变
 * 必须重启项目，这点怎么改进下？？？
 * 资源源数据定义，即定义某一资源可以被哪些角色访问建立资源与权限的对应关系
 * 也可以直接使用Spring提供的类 DefaultFilterInvocationSecurityMetadataSource
 */
public class WebSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

    @Autowired
    private RoleAndMenuMapper roleAndMenuMapper;

    /**
     * 初始化资源配置
     * <p>
     * spring 调用该方法的方式有2种
     * 方式1，方法上加注解： @PostConstruct
     * 方式2，配置文件中 init-method 属性指定：
     * <beans:bean id="webSecurityMetadataSource" init-method="initResource"
     * class="com.spring.security.security.security.WebSecurityMetadataSource"/>
     */
    @PostConstruct
    public void initResource() {
        resourceMap.clear();

        //取得当前系统所有可用角色
        Set<AppRole> roles = roleAndMenuMapper.getAppRolesByUserId(null, 1);

        for (AppRole role : roles) {
            this.loadRole(role);
        }
    }

    private void loadRole(AppRole role) {
        //这里的 role 参数为自己定义的，要和 UserDetailsService 中的
        // SimpleGrantedAuthority 参数对应
        //role 参数也可以直接使用角色名
        ConfigAttribute ca = new SecurityConfig(role.getName());

        role.setAppMenus(roleAndMenuMapper.getAppMenusByRoleId(role.getId(), null));

        this.handleRoleAndMenu(role.getAppMenus(), ca);
    }

    /**
     * 处理角色权限的依赖关系
     *
     * @param menus 权限列表
     * @param ca    角色名称
     */
    private void handleRoleAndMenu(Set<AppMenu> menus, ConfigAttribute ca) {
        for (AppMenu menu : menus) {
            String menuUrl = menu.getMenuurl();
            if (StringUtils.isEmpty(menuUrl)) {
                continue;
            }
            if (resourceMap.containsKey(menuUrl)) {
                resourceMap.get(menuUrl).add(ca);//权限角色一对多的关系//权限的KEY为URL
            } else {
                Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                atts.add(ca);
                resourceMap.put(menuUrl, atts);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        for (String resourceURL : resourceMap.keySet()) {
            //AntPathRequestMatcher : 来自于Ant项目，是一种简单易懂的路径匹配策略。
            //RegexRequestMatcher : 如果 AntPathRequestMatcher 无法满足需求，
            //还可以选择使用更强大的RegexRequestMatcher，它支持使用正则表达式对URL地址进行匹配
//            RequestMatcher requestMatcher = new AntPathRequestMatcher(resourceURL);
            RequestMatcher requestMatcher = new RegexRequestMatcher(resourceURL, request.getMethod(), true);
            if (requestMatcher.matches(request)) {
                return resourceMap.get(resourceURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void reloadResource() {
        //
        this.initResource();
    }

    public static Map<String, Collection<ConfigAttribute>> getResourceMap() {
        return resourceMap;
    }

    public static void setResourceMap(Map<String, Collection<ConfigAttribute>> resourceMap) {
        WebSecurityMetadataSource.resourceMap = resourceMap;
    }

}
