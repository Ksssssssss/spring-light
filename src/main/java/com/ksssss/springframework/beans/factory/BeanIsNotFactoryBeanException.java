package com.ksssss.springframework.beans.factory;

import cn.hutool.core.util.StrUtil;
import com.ksssss.springframework.beans.BeansException;

/**
 * @author ksssss
 * @date 2022/2/24 下午10:09
 */
public class BeanIsNotFactoryBeanException extends BeansException {

    private final String beanName;
    private final Class<?> clazz;

    public BeanIsNotFactoryBeanException(String beanName, Class<?> clazz) {
        super(StrUtil.format("bean is not factoryBean,beanName:{},clazz:{}",beanName,clazz));
        this.beanName = beanName;
        this.clazz = clazz;
    }
}
