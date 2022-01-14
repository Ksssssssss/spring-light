package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.BeanCreationException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;

/**
 * @author ksssss
 * @date 2022/1/13 下午11:11
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements
        AutowireCapableBeanFactory {

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) throws BeansException {

        Class clazz = beanDefinition.getClazz();
        Object bean = null;
        try {
            bean = clazz.newInstance();
        } catch (
                InstantiationException e) {
            throw new BeanCreationException("无法实例化类 ’" + clazz.getCanonicalName() + "' 可能是由于该类抽象类、接口、无默认构造函数", e);
        } catch (
                IllegalAccessException e) {
            throw new BeanCreationException("无法实例化类 ’" + clazz.getCanonicalName() + "' 可能是由于无权访问指定类", e);
        }
        registerSingleton(beanName, bean);
        return bean;
    }
}
