package com.ksssss.springframework.beans.factory;

import com.ksssss.springframework.beans.BeansException;

/**
 * Bean容器
 *
 * @author ksssss
 * @date 2022/1/13 下午1:25
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;
}
