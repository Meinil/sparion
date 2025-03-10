package com.meinil.common.core.utlis;

/**
 * @author Meinil
 * @date 2025/3/9
 * @description
 */
public class ArrayUtil {
    private ArrayUtil() {}

    /**
     * 判断数组是否为空
     * @param array 数组
     * @return 是否为空
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不为空
     * @param array 数组
     * @return 是否不为空
     */
    public static <T> boolean isNotEmpty(T[] array) {
        return isEmpty(array);
    }
}
