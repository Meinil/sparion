package com.meinil.sparion.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.meinil.sparion.common.entity.LoginUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Meinil
 * @date 2022/9/25
 * @description MybatisPlus配置类
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "deleted", () -> false, Boolean.class);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(principal)) {
            this.strictInsertFill(metaObject, "createUserId", () -> -1L, Long.class);
            this.strictInsertFill(metaObject, "updateUserId", () -> -1L, Long.class);
        } else {
            this.strictInsertFill(metaObject, "createUserId", () -> ((LoginUser) principal).getId(), Long.class);
            this.strictInsertFill(metaObject, "updateUserId", () -> ((LoginUser) principal).getId(), Long.class);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if ("anonymousUser".equals(principal)) {
            this.strictUpdateFill(metaObject, "updateUserId", () -> -1L, Long.class);
        } else {
            this.strictUpdateFill(metaObject, "updateUserId", () -> ((LoginUser) principal).getId(), Long.class);
        }
    }
}