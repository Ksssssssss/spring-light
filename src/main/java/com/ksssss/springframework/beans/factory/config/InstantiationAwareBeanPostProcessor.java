package com.ksssss.springframework.beans.factory.config;

import com.ksssss.springframework.beans.BeansException;

/**
 * 实例化的后处理器
 *
 * @author ksssss
 * @date 2022/3/9 下午11:54
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在实例化之前返回代理对象
     */
    default Object postProcessorBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }
}
