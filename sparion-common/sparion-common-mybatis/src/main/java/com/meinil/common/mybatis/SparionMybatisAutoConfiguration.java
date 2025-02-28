package com.meinil.common.mybatis;

import com.meinil.common.mybatis.Interceptor.SnowflakeIdInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description
 */
@Configuration
public class SparionMybatisAutoConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        // 添加自定义插件
        Interceptor[] plugins = { new SnowflakeIdInterceptor() };
        factoryBean.setPlugins(plugins);

        return factoryBean.getObject();
    }

}
