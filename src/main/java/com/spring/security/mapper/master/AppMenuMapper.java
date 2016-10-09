package com.spring.security.mapper.master;

import com.spring.security.domain.master.AppMenu;
import com.spring.security.domain.master.AppMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMenuMapper {
    int countByExample(AppMenuExample example);

    int deleteByExample(AppMenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppMenu record);

    int insertSelective(AppMenu record);

    List<AppMenu> selectByExample(AppMenuExample example);

    AppMenu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppMenu record, @Param("example") AppMenuExample example);

    int updateByExample(@Param("record") AppMenu record, @Param("example") AppMenuExample example);

    int updateByPrimaryKeySelective(AppMenu record);

    int updateByPrimaryKey(AppMenu record);
}