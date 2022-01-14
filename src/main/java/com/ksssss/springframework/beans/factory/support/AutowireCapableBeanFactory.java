package com.ksssss.springframework.beans.factory.support;

import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.BeanFactory;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;

/**
 * 提供创建bean
 *
 * @author ksssss
 * @date 2022/1/13 下午1:41
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
