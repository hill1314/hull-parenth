package com.hull.common.utils;

import com.hull.common.web.AccessToken;

/**
 * webUtil
 *
 * @author hull
 * @create 2017-10-27 下午8:27
 * @desc
 **/
public class WebUtil {

    /**
     * 每个线程拥有自己的token对象
     */
    public static ThreadLocal<AccessToken> accessTokenThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前用户ID
     * @return
     */
    public static Long getUid() {
        AccessToken accessToken = getAccessToken();
        if (accessToken != null) {
            return accessToken.getUserId();
        } else {
            return 0L;
        }
    }

    public static AccessToken getAccessToken() {
        return accessTokenThreadLocal.get();
    }
}
