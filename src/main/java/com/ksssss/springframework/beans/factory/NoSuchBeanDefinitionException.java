package com.ksssss.springframework.beans.factory;

import com.ksssss.springframework.beans.BeansException;

/**
 * @author ksssss
 * @date 2022/1/12 下午11:22
 */
public class NoSuchBeanDefinitionException extends BeansException {

    private String beanName;

    public NoSuchBeanDefinitionException(String beanName) {
        super("BeanDefinition ‘" + beanName + "’ 没有被定义");
        this.beanName = beanName;
    }

    public NoSuchBeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
