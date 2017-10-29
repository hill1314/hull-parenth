package com.hull.entity.db;

import com.hull.common.base.BaseEntity;

import java.util.Date;

/**
WARNING: 
this code is generated by CodeMonkey
please DO NOT modify this file
**/
public class SysRole extends BaseEntity {
  private Integer roleId;
  private String roleName;
  /**
  0-表示系统角色.
  **/
  private Integer roleType;
  /**
  角色描述
  **/
  private String roleDesc;
  private Date updateTime;
  private Date createTime;
  /**
  1  有效
            0  无效
  **/
  private Long validFalg;

  public Integer getRoleId() {
  	return roleId;
  }
	
  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }
	
  public String getRoleName() {
  	return roleName;
  }
	
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
	
  public Integer getRoleType() {
  	return roleType;
  }
	
  public void setRoleType(Integer roleType) {
    this.roleType = roleType;
  }
	
  public String getRoleDesc() {
  	return roleDesc;
  }
	
  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }
	
  public Date getUpdateTime() {
  	return updateTime;
  }
	
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
	
  public Date getCreateTime() {
  	return createTime;
  }
	
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
	
  public Long getValidFalg() {
  	return validFalg;
  }
	
  public void setValidFalg(Long validFalg) {
    this.validFalg = validFalg;
  }
	
}
