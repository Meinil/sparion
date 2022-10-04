package com.meinil.sparion.common;

import com.meinil.sparion.common.utils.CipherUtils;
import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

/**
 * @author Meinil
 * @date 2022/10/5
 * @description
 */
public class CipherUtilsTest {
    @Test
    public void test1() throws GeneralSecurityException {
        String key = CipherUtils.generateSecret(16);
        String encrypt = CipherUtils.encrypt(key, "ABCD!@#$");

        String decrypt = CipherUtils.decrypt(key, encrypt);
        System.out.println(decrypt);
    }
}
