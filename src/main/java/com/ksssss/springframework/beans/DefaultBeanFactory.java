package com.ksssss.springframework.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的bean容器，主要是对bean注册后处理
 *
 * @author ksssss
 * @date 2022/1/10 下午11:14
 */
public class DefaultBeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();

    public Object getBean(String beanName) {
        return this.beanMap.get(beanName);
    }

    public void registerBean(String beanName, Object bean) {
        this.beanMap.put(beanName, bean);
    }

}
