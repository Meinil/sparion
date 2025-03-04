package com.meinil.common.core.utlis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    /**
     * 返回一个空List
     * @return
     */
    public static <T> List<T> emptyList() {
        return Collections.emptyList();
    }
}
