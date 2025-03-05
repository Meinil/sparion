package com.meinil.common.mybatis.Interceptor;

import com.meinil.common.mybatis.utils.SnowflakeIdGenerator;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description 属性自动填充拦截器
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class AutoFillInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object parameter = args[1];

        if (Objects.isNull(parameter)) {
            return invocation.proceed();
        }

        MappedStatement mappedStatement = (MappedStatement) args[0];
        String sqlCommandType = mappedStatement.getSqlCommandType().toString();

        LocalDateTime now = LocalDateTime.now();
        if ("INSERT".equals(sqlCommandType)) {
            handleInsert(parameter, now);
        } else if ("UPDATE".equals(sqlCommandType)) {
            handleUpdate(parameter, now);
        }

        return invocation.proceed();
    }

    /**
     * 处理插入时的自动填充
     * @param parameter 要填充的对象
     */
    private void handleInsert(Object parameter, LocalDateTime now) throws IllegalAccessException {
        // 如果 ID 为空，则生成雪花 ID
        Field idField = getField(parameter.getClass(), "id");
        if (idField != null) {
            idField.setAccessible(true);
            if (idField.get(parameter) == null) {
                idField.set(parameter, SnowflakeIdGenerator.nextId());
            }
            idField.setAccessible(false);
        }

        // 自动插入创建时间
        Field createTimeField = getField(parameter.getClass(), "createTime");
        if (createTimeField != null) {
            createTimeField.setAccessible(true);
            if (createTimeField.get(parameter) == null) {
                createTimeField.set(parameter, now);
            }
            createTimeField.setAccessible(false);
        }


        // 自动插入更新时间
        Field updateTimeField = getField(parameter.getClass(), "updateTime");
        if (updateTimeField != null) {
            updateTimeField.setAccessible(true);
            if (updateTimeField.get(parameter) == null) {
                updateTimeField.set(parameter, now);
            }
            updateTimeField.setAccessible(false);
        }
    }

    /**
     * 处理更新时的自动填充
     * @param parameter 要填充的对象
     */
    private void handleUpdate(Object parameter, LocalDateTime now) throws IllegalAccessException {
        // 自动插入更新时间
        Field updateTimeField = getField(parameter.getClass(), "updateTime");
        if (updateTimeField.get(parameter) == null) {
            updateTimeField.set(parameter, now);
        }
        updateTimeField.setAccessible(true);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 可以在这里设置一些配置属性，如果需要的话
    }

    private static Field getField(Class<?> clazz, String fieldName) {
        if (clazz == null) {
            return null;
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
