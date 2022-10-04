package com.meinil.sparion.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Random;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description 加解密工具类
 */
public class CipherUtils {


    /**
     * 生成秘钥
     * @param length 秘钥的长度
     * @return 秘钥
     */
    public static String generateSecret(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append((char)(random.nextInt(94) + 33));
        }
        return sb.toString();
    }

    /**
     * 加密
     * @param key 秘钥
     * @param input 待加密字符串
     * @return 加密字符串
     * @throws GeneralSecurityException
     */
    public static String encrypt(String key, String input) throws GeneralSecurityException {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(cipher.doFinal(inputBytes));
    }

    /**
     * 解密
     * @param key 秘钥
     * @param input 待解密字符串
     * @return 原文字符串
     * @throws GeneralSecurityException
     */
    public static String decrypt(String key, String input) throws GeneralSecurityException {
        // Base64解码
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] inputBytes = decoder.decode(input);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return new String(cipher.doFinal(inputBytes));
    }
}
