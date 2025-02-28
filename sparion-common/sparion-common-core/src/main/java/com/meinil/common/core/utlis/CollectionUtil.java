package com.meinil.common.core.utlis;

import java.util.Collection;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description 集合工具类
 */
public class CollectionUtil {

    private CollectionUtil() {}

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
