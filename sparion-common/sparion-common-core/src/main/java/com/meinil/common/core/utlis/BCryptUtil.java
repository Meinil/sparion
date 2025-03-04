package com.meinil.common.core.utlis;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Meinil
 * @date 2025/2/28
 * @description 加解密工具类
 */
public class BCryptUtil {

    private BCryptUtil() {}

    /**
     * 生成盐值并加密密码
     *
     * @param password 原始密码
     * @return 加密后的密码字符串
     */
    public static String hashPassword(String password) {
        // 生成盐值
        String salt = BCrypt.gensalt();
        // 加密密码
        return BCrypt.hashpw(password, salt);
    }

    /**
     * 验证密码是否匹配
     *
     * @param password 用户输入的密码
     * @param hashed   存储的加密密码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

    public static void main(String[] args) {
        String hashed = hashPassword("admin123");
        System.out.println(hashed);
    }
}
