package com.ksssss.springframework.beans.factory;

import com.ksssss.springframework.beans.BeansException;

/**
 * Bean容器
 *
 * @author ksssss
 * @date 2022/1/13 下午1:25
 */
public interface BeanFactory {

    String FACTORY_BEAN_PREFIX = "&";

    Object getBean(String name) throws BeansException;
}
