package com.meinil.common.core.serializer;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * @author Meinil
 * @date 2025/3/20
 * @description
 */
public class LocalDateSerializer implements ObjectWriter<LocalDate> {
    @Override
    public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {

    }
}
