package com.meinil.common.core.utlis;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description 字符串工具类
 */
public class StringUtil extends StringUtils {

    private StringUtil() {}

    public static boolean notEquals(final CharSequence cs1, final CharSequence cs2) {
        return !equals(cs1, cs2);
    }
}
