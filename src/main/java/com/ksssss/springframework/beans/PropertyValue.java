package com.ksssss.springframework.beans;

import lombok.Data;

/**
 * 保存属性信息的类
 *
 * @author ksssss
 * @date 2022/1/12 上午12:01
 */
@Data
public class PropertyValue {

    private String name;
    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
