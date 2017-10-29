package com.hull.service;

import com.hull.common.base.Base;
import com.hull.entity.db.SysMenu;
import com.hull.entity.db.SysRole;
import com.hull.entity.db.SysUser;
import com.hull.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;


    public SysUser getSysUserByUserName(String userName) {
        return sysUserMapper.getByUserName(userName);
    }

    /**
     * 查询用户的角色
     * @param userId
     * @return
     */
    public List<Integer> queryRoleIdsByUserId(Integer userId) {
        return sysUserRoleMapper.queryRoleIdsByUserId(userId);
    }

    /**
     * 查询 角色对应的菜单权限
     * @param roleIds
     * @return
     */
    public List<SysMenu> listMenu(List<Integer> roleIds) {
        return sysMenuMapper.getMenuByRoleId(roleIds);
    }
}
