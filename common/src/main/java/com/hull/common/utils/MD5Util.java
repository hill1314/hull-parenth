package com.hull.common.utils;

import java.security.MessageDigest;

/**
 * MD5 加解密
 *
 * @author hull
 * @create 2017-10-27 下午9:50
 * @desc
 **/
public class MD5Util {

    private static MD5Util md5Util = null;

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

    /**
     * 单例
     * @return
     */
    public static synchronized MD5Util getInstance() {
        if (md5Util == null){
            md5Util = new MD5Util();
        }
        return md5Util;
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 自定义的算法，将二进制转为 字符串
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    /**
     * Java 自带的 信息摘要算法 (such as DSA, RSA, MD5 or SHA-1)
     * @param origin
     * @return
     */
    public String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encrypt(String str) {
        return getInstance().MD5Encode(str);
    }


    public static void main(String[] args) {

        System.out.println(MD5Util.encrypt("admin"));
    }
}
