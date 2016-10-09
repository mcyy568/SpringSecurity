package com.spring.security.mapper.master;

import com.spring.security.domain.master.AppRoleMenu;
import com.spring.security.domain.master.AppRoleMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppRoleMenuMapper {
    int countByExample(AppRoleMenuExample example);

    int deleteByExample(AppRoleMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppRoleMenu record);

    int insertSelective(AppRoleMenu record);

    List<AppRoleMenu> selectByExample(AppRoleMenuExample example);

    AppRoleMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppRoleMenu record, @Param("example") AppRoleMenuExample example);

    int updateByExample(@Param("record") AppRoleMenu record, @Param("example") AppRoleMenuExample example);

    int updateByPrimaryKeySelective(AppRoleMenu record);

    int updateByPrimaryKey(AppRoleMenu record);
}