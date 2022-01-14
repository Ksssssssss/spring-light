package com.ksssss.springframework.beans.factory.support;

import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.BeanFactory;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;

/**
 * 提供bean
 *
 * @author ksssss
 * @date 2022/1/13 下午1:39
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
