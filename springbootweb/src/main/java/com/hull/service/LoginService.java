package com.hull.service;

import com.hull.common.base.Base;
import com.hull.entity.db.SysUser;
import com.hull.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录相关 服务
 *
 * @author hull
 * @create 2017-10-27 下午9:41
 * @desc
 **/
@Service
public class LoginService extends Base{

    @Resource
    private SysUserMapper sysUserMapper;

    public SysUser getSysUserByUserName(String userName) {
        return sysUserMapper.getByUserName(userName);
    }
}
