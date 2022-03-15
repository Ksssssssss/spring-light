package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.factory.BeanCreationException;
import com.ksssss.springframework.beans.factory.BeanCurrentlyInCreationException;
import com.ksssss.springframework.beans.factory.ObjectFactory;
import com.ksssss.springframework.beans.factory.SingletonBeanStoreException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * SingletonBeanRegistry默认的实现
 *
 * @author ksssss
 * @date 2022/1/13 下午12:27
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    private final Map<String, ObjectFactory<?>> singletonFactorys = new ConcurrentHashMap<>();

    private final Set<String> singletonsCurrentlyInCreation = new CopyOnWriteArraySet<>();

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
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null) {
                ObjectFactory<?> singletonFactory = this.singletonFactorys.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    this.earlySingletonObjects.put(beanName, singletonObject);
                    this.singletonFactorys.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    public void addSingleton(String beanName, Object singleton) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonObjects.put(beanName, singleton);
            this.earlySingletonObjects.remove(beanName);
            this.singletonFactorys.remove(beanName);
        }
    }

    public Object getSingleton(String beanName, ObjectFactory<?> objectFactory) {
        beforeSingletonCreation(beanName);
        Object bean = objectFactory.getObject();
        afterSingletonCreation(beanName);
        addSingleton(beanName, bean);
        return bean;
    }

    public void addSingletonFactory(String beanName, ObjectFactory<?> objectFactory) {
        Assert.notNull(objectFactory);
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactorys.put(beanName, objectFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.contains(beanName);
    }

    public void beforeSingletonCreation(String beanName) {
        if (!this.singletonsCurrentlyInCreation.add(beanName)) {
            throw new BeanCurrentlyInCreationException(beanName);
        }
    }

    public void afterSingletonCreation(String beanName) {
        if (!this.singletonsCurrentlyInCreation.remove(beanName)) {
            throw new IllegalStateException(StrUtil.format("{}，isn't currently in creation", beanName));
        }
    }
}
