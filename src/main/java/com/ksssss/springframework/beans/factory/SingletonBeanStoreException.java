package com.ksssss.springframework.beans.factory;

import com.ksssss.springframework.beans.BeansException;

/**
 * @author ksssss
 * @date 2022/1/12 下午11:15
 */
public class SingletonBeanStoreException extends BeansException {

    private String beanName;

    public SingletonBeanStoreException(String beanName) {
        super("单例 ‘" + beanName + "’ 已定义 ");
        this.beanName = beanName;
    }
}
