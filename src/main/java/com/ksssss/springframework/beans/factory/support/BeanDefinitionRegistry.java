package com.ksssss.springframework.beans.factory.support;

import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;

/**
 * 对BeanDefinition的各种增删改操作
 *
 * @author ksssss
 * @date 2022/1/12 下午10:53
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
