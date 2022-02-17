package com.ksssss.springframework.beans.factory.support;

/**
 * @author ksssss
 * @date 2022/1/24 下午11:56
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    protected BeanDefinitionRegistry getRegistry() {
        return registry;
    }
}
