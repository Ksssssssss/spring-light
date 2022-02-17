package com.ksssss.springframework.beans.factory.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * BeanDefinition持有者
 * @author ksssss
 * @date 2022/2/17 下午11:00
 */
@Getter
@EqualsAndHashCode
public class BeanDefinitionHolder {
    private final BeanDefinition beanDefinition;
    private final String beanName;

    public BeanDefinitionHolder(String beanName,BeanDefinition beanDefinition) {
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
    }
}
