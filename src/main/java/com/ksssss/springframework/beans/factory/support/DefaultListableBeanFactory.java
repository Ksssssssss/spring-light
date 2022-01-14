package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.BeanDefinitionStoreException;
import com.ksssss.springframework.beans.factory.NoSuchBeanDefinitionException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ksssss
 * @date 2022/1/13 下午11:29
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException(beanName);
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Assert.notEmpty(beanName);
        Assert.notNull(beanDefinition);
        BeanDefinition oldBeanDefinition = this.beanDefinitionMap.get(beanName);
        if (oldBeanDefinition != null) {
            throw new BeanDefinitionStoreException(beanName);
        }
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }
}
