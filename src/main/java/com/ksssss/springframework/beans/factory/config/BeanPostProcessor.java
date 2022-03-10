package com.ksssss.springframework.beans.factory.config;

import com.ksssss.springframework.beans.BeansException;

/**
 * 后处理器
 *
 * @author ksssss
 * @date 2022/3/9 下午11:48
 */
public interface BeanPostProcessor {

    /**
     * 在init方法之前如（afterPropertiesSet or a custom init-method）属性注入之后
     */
    default Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在init方法之后如（afterPropertiesSet or a custom init-method）属性注入之后,FactoryBean创建对象后会调用
     */
    default Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
