package com.blog.utils;

import com.blog.exception.APIException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xck
 */
public class MD5Utils {

    private static final String SALT = "xck666";

    public static String md5(String string) {
        // 创建 MessageDigest 实例并指定算法为 MD5
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new APIException(e.getMessage());
        }
        md.update(SALT.getBytes());

        // 将输入字符串转换为字节数组
        byte[] inputBytes = string.getBytes();

        // 计算 MD5 哈希值
        byte[] hashBytes = md.digest(inputBytes);

        // 将字节数组转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}