package com.ksssss.springframework.beans;

import java.util.HashSet;
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
    private String value;

    public PropertyValue(String name, String value) {
        HashSet<Object> objects = new HashSet<>();
        this.name = name;
        this.value = value;
    }
}
