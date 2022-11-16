package com.ksssss.springframework.beans.factory;

/**
 *
 * @author ksssss
 * @date 2022/3/16 下午10:32
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory);
}
