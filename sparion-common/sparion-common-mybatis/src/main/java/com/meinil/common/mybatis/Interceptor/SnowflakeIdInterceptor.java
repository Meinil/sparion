package com.meinil.common.mybatis.Interceptor;

import com.meinil.common.mybatis.utils.SnowflakeIdGenerator;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description 自动插入id
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class SnowflakeIdInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取参数对象
        Object parameter = invocation.getArgs()[1];

        if (parameter != null) {
            // 假设实体类有一个名为 "id" 的 Long 类型字段
            Field idField = getField(parameter.getClass(), "id");
            idField.setAccessible(true);

            // 如果 ID 为空，则生成雪花 ID
            if (idField.get(parameter) == null) {
                idField.set(parameter, SnowflakeIdGenerator.nextId());
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以在这里设置一些配置属性，如果需要的话
    }

    private static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        if (clazz == null) {
            throw new NoSuchFieldException(fieldName);
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        // 如果当前类没有找到该字段，继续查找父类
        return getField(clazz.getSuperclass(), fieldName);
    }
}
