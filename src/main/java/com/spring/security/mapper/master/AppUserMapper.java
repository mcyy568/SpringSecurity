package com.spring.security.mapper.master;

import com.spring.security.domain.master.AppUser;
import com.spring.security.domain.master.AppUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppUserMapper {
    int countByExample(AppUserExample example);

    int deleteByExample(AppUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppUser record);

    int insertSelective(AppUser record);

    List<AppUser> selectByExample(AppUserExample example);

    AppUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppUser record, @Param("example") AppUserExample example);

    int updateByExample(@Param("record") AppUser record, @Param("example") AppUserExample example);

    int updateByPrimaryKeySelective(AppUser record);

    int updateByPrimaryKey(AppUser record);
}