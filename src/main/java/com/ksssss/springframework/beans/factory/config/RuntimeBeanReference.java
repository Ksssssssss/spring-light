package com.ksssss.springframework.beans.factory.config;

import lombok.Data;

/**
 * bean引用
 *
 * @author ksssss
 * @date 2022/2/21 下午11:32
 */
@Data
public class RuntimeBeanReference {

    private final String name;
    private final String beanType;

    public RuntimeBeanReference(String name) {
        this(name, null);
    }

    public RuntimeBeanReference(String name, String beanType) {
        this.name = name;
        this.beanType = beanType;
    }
}
