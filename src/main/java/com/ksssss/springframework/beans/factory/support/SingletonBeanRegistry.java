package com.ksssss.springframework.beans.factory.support;

import com.ksssss.springframework.beans.BeansException;

/**
 * 单例的注册和获取
 *
 * @author ksssss
 * @date 2022/1/13 下午12:27
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object SingletonObject);

    Object getSingleton(String beanName);
}
