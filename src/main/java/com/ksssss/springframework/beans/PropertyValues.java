package com.ksssss.springframework.beans;

import lombok.Data;

/**
 * 保存多个PropertyValues
 *
 * @author ksssss
 * @date 2022/1/12 上午12:01
 */
public interface PropertyValues {
    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String propertyName);

    boolean contains(String propertyName);

    boolean isEmpty();
}
