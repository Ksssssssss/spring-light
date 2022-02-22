package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.StrUtil;
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
    public Object getBean(String name) throws BeansException {
        String beanName = transformName(name);
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected String transformName(String name) {
        String beanName = name;
        if (StrUtil.startWith(name, FACTORY_BEAN_PREFIX)) {
            beanName = StrUtil.removePrefix(name, FACTORY_BEAN_PREFIX);
        }
        return beanName;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
