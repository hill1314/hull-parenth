package com.hull.entity.db;

import com.hull.common.base.BaseEntity;

import java.util.Date;

/**
WARNING: 
this code is generated by CodeMonkey
please DO NOT modify this file
**/
public class SysRoleMenu extends BaseEntity {
  private Integer roleId;
  private Integer menuId;
  private Date createTime;

  public Integer getRoleId() {
  	return roleId;
  }
	
  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }
	
  public Integer getMenuId() {
  	return menuId;
  }
	
  public void setMenuId(Integer menuId) {
    this.menuId = menuId;
  }
	
  public Date getCreateTime() {
  	return createTime;
  }
	
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
	
}
