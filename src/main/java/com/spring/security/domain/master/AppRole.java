package com.spring.security.domain.master;

import java.util.Date;
import java.util.Set;

public class AppRole {
    private Integer id;

    private String name;

    private Date createtime;

    private Date lastupdate;

    private Integer status;

    private String descn;

    private Set<AppMenu> appMenus;

    public Set<AppMenu> getAppMenus() {
        return appMenus;
    }

    public void setAppMenus(Set<AppMenu> appMenus) {
        this.appMenus = appMenus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn == null ? null : descn.trim();
    }
}