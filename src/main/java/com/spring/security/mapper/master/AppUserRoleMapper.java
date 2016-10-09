package com.spring.security.mapper.master;

import com.spring.security.domain.master.AppUserRole;
import com.spring.security.domain.master.AppUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppUserRoleMapper {
    int countByExample(AppUserRoleExample example);

    int deleteByExample(AppUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppUserRole record);

    int insertSelective(AppUserRole record);

    List<AppUserRole> selectByExample(AppUserRoleExample example);

    AppUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppUserRole record, @Param("example") AppUserRoleExample example);

    int updateByExample(@Param("record") AppUserRole record, @Param("example") AppUserRoleExample example);

    int updateByPrimaryKeySelective(AppUserRole record);

    int updateByPrimaryKey(AppUserRole record);
}