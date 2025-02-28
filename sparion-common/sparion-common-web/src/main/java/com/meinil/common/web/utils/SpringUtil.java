package com.meinil.common.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Meinil
 * @date 2025/2/22
 * @description
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtil.context = context;
    }

    public static  <T> T getBean(Class<T> clazz) {
        return SpringUtil.context.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return SpringUtil.context.getBean(name, clazz);
    }

    public static Object getBean(String name) {
        return SpringUtil.context.getBean(name);
    }
}
