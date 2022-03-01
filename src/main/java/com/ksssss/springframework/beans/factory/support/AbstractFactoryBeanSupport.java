package com.ksssss.springframework.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 支持工厂bean
 *
 * @author ksssss
 * @date 2022/2/24 下午10:55
 */
public abstract class AbstractFactoryBeanSupport extends DefaultSingletonBeanRegistry {

    protected final Map<String, Object> factoryBeanObjects = new ConcurrentHashMap<>();

    protected Object getCacheObjectForFactoryBean(String beanName) {
        return this.factoryBeanObjects.get(beanName);
    }

    protected void putFactoryBeanObject(String beanName, Object beanInstance) {
        this.factoryBeanObjects.put(beanName, beanInstance);
    }
}
