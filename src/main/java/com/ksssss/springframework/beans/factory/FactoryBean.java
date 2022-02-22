package com.ksssss.springframework.beans.factory;

/**
 * 工厂Bean
 *
 * @author ksssss
 * @date 2022/2/22 下午11:49
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;

    boolean isSingleton();
}
