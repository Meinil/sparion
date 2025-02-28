package com.meinil.common.core.utlis;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description 字符串工具类
 */
public class StringUtil {
    private StringUtil() {}


    /**
     * 判断字符串是否为空
     * @param cs 待判断的字符
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !StringUtil.isEmpty(cs);
    }
}
