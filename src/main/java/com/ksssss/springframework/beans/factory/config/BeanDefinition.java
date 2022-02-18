package com.ksssss.springframework.beans.factory.config;

import com.ksssss.springframework.beans.MutablePropertyValues;
import lombok.Data;

/**
 * 保存bean的构造参数，属性，class，scope
 *
 * @author ksssss
 * @date 2022/1/11 下午11:38
 */
@Data
public class BeanDefinition {

    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";

    private String scope;
    private Class clazz;
    private MutablePropertyValues propertyValues = new MutablePropertyValues();

}
