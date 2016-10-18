package com.spring.security.mapper.master;

import com.spring.security.domain.master.AppRole;
import com.spring.security.domain.master.AppRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppRoleMapper {
    int countByExample(AppRoleExample example);

    int deleteByExample(AppRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppRole record);

    int insertSelective(AppRole record);

    List<AppRole> selectByExample(AppRoleExample example);

    AppRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppRole record, @Param("example") AppRoleExample example);

    int updateByExample(@Param("record") AppRole record, @Param("example") AppRoleExample example);

    int updateByPrimaryKeySelective(AppRole record);

    int updateByPrimaryKey(AppRole record);
}