package com.hull.common.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hull.common.base.SerializableEntity;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局Token对象，用户获取从Header传递过来的全局参数
 *
 * @author hull
 * @create 2017-10-27 下午8:33
 * @desc
 **/
public class AccessToken extends SerializableEntity{

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备信息
     */
    private String userAgent;
    /**
     * 签名串
     */
    private String token;
    /**
     * 请求ID
     */
    private String traceId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public AccessToken(HttpServletRequest request) {
        if (request != null) {
            this.userId = NumberUtils.toLong(request.getHeader("X-ui"), 0L);
            this.deviceId = request.getHeader("X-di");
            this.token = request.getHeader("X-token");
            this.traceId = request.getHeader("X-traceId");
            this.userAgent = request.getHeader("User-Agent");
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
