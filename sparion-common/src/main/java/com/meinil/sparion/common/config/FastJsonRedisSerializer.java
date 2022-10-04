package com.meinil.sparion.common.config;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.expression.spel.SpelParserConfiguration;

import java.nio.charset.Charset;


/**
 * @author Meinil
 * @date 2022/9/24
 * @description Redis使用FastJson序列化
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static {
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);

    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t,
                JSONWriter.Feature.WriteClassName,                   // 序列化类的名称
                JSONWriter.Feature.WriteMapNullValue,                // 保留map空的字段
                JSONWriter.Feature.WriteNullNumberAsZero,            // Number类型null转为0
                JSONWriter.Feature.WriteNullListAsEmpty,             // 空List序列化为[]
                JSONWriter.Feature.WriteNullStringAsEmpty,           // 空字符串序列化为""
                JSONWriter.Feature.WriteNullBooleanAsFalse           // 将Boolean类型的null转成false
        ).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }


    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
