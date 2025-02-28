package com.meinil.common.mybatis.utils;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description 雪花id生成
 */
public class SnowflakeIdGenerator {
    static {
        YitIdHelper.setIdGenerator(new IdGeneratorOptions());
    }

    public static Long nextId() {
        return Long.valueOf(YitIdHelper.nextId());
    }
}
