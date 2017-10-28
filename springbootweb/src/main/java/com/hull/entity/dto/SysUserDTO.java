package com.hull.entity.dto;

import com.hull.common.base.SerializableEntity;

/**
 * 系统用户
 *
 * @author hull
 * @create 2017-10-27 下午9:03
 * @desc
 **/
public class SysUserDTO extends SerializableEntity{
    private Integer userId;
    private String userName;
    private String trueName;
    private String password;
    //验证码
    private String randcode;

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

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandcode() {
        return randcode;
    }

    public void setRandcode(String randcode) {
        this.randcode = randcode;
    }
}
