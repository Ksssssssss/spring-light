package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.SingletonBeanStoreException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SingletonBeanRegistry默认的实现
 *
 * @author ksssss
 * @date 2022/1/13 下午12:27
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName);
        Assert.notNull(singletonObject);
        Object oldSingletonObject = this.singletonObjects.get(beanName);
        if (oldSingletonObject != null) {
            throw new SingletonBeanStoreException(beanName);
        }
        this.singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
