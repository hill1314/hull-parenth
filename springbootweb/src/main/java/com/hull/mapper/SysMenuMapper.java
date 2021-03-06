package com.hull.mapper;


import com.hull.common.base.BaseMapper;
import com.hull.entity.db.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper {

    List<SysMenu> getMenuByRoleId(List<Integer> roleIds);
}