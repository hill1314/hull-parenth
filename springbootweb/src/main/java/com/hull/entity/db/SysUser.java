package com.hull.entity.db;

import com.hull.common.base.BaseEntity;

/**
WARNING: 
this code is generated by CodeMonkey
please DO NOT modify this file
**/
public class SysUser extends BaseEntity {
  private Integer userId;
  private String userName;
  private String realName;
  private String password;

  public Integer getUserId() {
  	return userId;
  }
	
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
	
  public String getUserName() {
  	return userName;
  }
	
  public void setUserName(String userName) {
    this.userName = userName;
  }
	
  public String getRealName() {
  	return realName;
  }
	
  public void setRealName(String realName) {
    this.realName = realName;
  }
	
  public String getPassword() {
  	return password;
  }
	
  public void setPassword(String password) {
    this.password = password;
  }
	
}
