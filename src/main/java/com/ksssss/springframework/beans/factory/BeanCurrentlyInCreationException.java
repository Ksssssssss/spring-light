package com.ksssss.springframework.beans.factory;

import cn.hutool.core.util.StrUtil;

/**
 * @author ksssss
 * @date 2022/3/13 下午10:36
 */
public class BeanCurrentlyInCreationException extends BeanCreationException {

    public BeanCurrentlyInCreationException(String beanName) {
        super(beanName, "请检查是否支持循环依赖");
    }
}
