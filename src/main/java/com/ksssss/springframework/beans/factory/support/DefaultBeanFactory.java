package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.BeanDefinitionStoreException;
import com.ksssss.springframework.beans.factory.NoSuchBeanDefinitionException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 默认的bean容器，主要是对bean注册后处理
 *
 * @author ksssss
 * @date 2022/1/10 下午11:14
 */
public class DefaultBeanFactory implements BeanDefinitionRegistry {

    private Map<String, Object> beanMap = new HashMap<>();

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    // 保存definition names ，目前不知道有撒作用
    private List<String> beanDefinitionNames = new CopyOnWriteArrayList<>();

    public Object getBean(String beanName) {
        return this.beanMap.get(beanName);
    }

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
            throw new BeanDefinitionStoreException(beanName + "的BeanDefinition已存在,不允许被覆盖");
        }
        this.beanDefinitionNames.add(beanName);
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }
}
