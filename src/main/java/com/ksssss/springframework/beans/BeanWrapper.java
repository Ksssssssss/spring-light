package com.ksssss.springframework.beans;

import lombok.Data;

/**
 *
 * bean持有器
 * @author ksssss
 * @date 2022/3/1 下午11:40
 */
@Data
public class BeanWrapper {
    private Object wrappedInstance;
    private Class<?> wrappedClass;
}
