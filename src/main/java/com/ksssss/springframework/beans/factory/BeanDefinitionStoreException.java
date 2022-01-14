package com.ksssss.springframework.beans.factory;

import com.ksssss.springframework.beans.BeansException;

/**
 * @author ksssss
 * @date 2022/1/12 下午11:15
 */
public class BeanDefinitionStoreException extends BeansException {

    private String beanName;

    public BeanDefinitionStoreException(String beanName) {
        super("BeanDefinition ‘" + beanName + "' 已经存在");
        this.beanName = beanName;
    }

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
