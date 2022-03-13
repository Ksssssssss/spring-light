package com.ksssss.springframework.beans.factory;

import cn.hutool.core.util.StrUtil;
import com.ksssss.springframework.beans.BeansException;

/**
 * @author ksssss
 * @date 2022/1/14 上午12:01
 */
public class BeanCreationException extends BeansException {

    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String beanName, String message) throws BeansException {
        super(StrUtil.format("创建bean失败: {},错误原因:{}", beanName, message));
    }

    public BeanCreationException(String message, Throwable cause) throws BeansException {
        super(message, cause);
    }
}
