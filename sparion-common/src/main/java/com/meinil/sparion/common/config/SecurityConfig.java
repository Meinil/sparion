package com.meinil.sparion.common.config;

import com.meinil.sparion.common.filters.DynamicallyUrlFilter;
import com.meinil.sparion.common.filters.JwtAuthenticationTokenFilter;
import com.meinil.sparion.common.handler.AccessDeniedHandlerImpl;
import com.meinil.sparion.common.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Meinil
 * @date 2022/9/24
 * @description SpringSecurity的配置
 */
@Configuration
public class SecurityConfig {
    /**
     * 处理token的过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private DynamicallyUrlFilter dynamicallyUrlFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 密码加密
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                // 关闭csrf
        return http.csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    // 登录,注册接口放行(只能匿名访问)
                    .antMatchers("/user/login", "/user/register").anonymous()
                    // 测试接口放行(登录未登录都放行)
                    .antMatchers("/**").permitAll()
                    // 除上面外的所有请求全部需要鉴权认证
                    .anyRequest().authenticated()
                    .and()
                // 添加自定义过滤器
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(dynamicallyUrlFilter, ExceptionTranslationFilter.class)

                // 自定义异常处理
                .exceptionHandling()
                    .authenticationEntryPoint(new AuthenticationEntryPointImpl())
                    .accessDeniedHandler(new AccessDeniedHandlerImpl())
                    .and()

                // 允许跨域
                .cors()
                    .and()
                .build();
    }


    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
