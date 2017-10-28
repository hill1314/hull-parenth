package com.hull.mapper;

import com.hull.common.base.BaseMapper;
import com.hull.entity.db.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<Long,SysUser> {

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    SysUser getByUserName(@Param("userName") String userName);
}