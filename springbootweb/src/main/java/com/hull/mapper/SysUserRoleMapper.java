package com.hull.mapper;

import com.hull.common.base.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper extends BaseMapper {

    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    List<Integer> queryRoleIdsByUserId(Integer userId);
}