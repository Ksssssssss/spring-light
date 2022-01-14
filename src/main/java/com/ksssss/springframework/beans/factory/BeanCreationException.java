package com.ksssss.springframework.beans.factory;

import com.ksssss.springframework.beans.BeansException;

/**
 * @author ksssss
 * @date 2022/1/14 上午12:01
 */
public class BeanCreationException extends BeansException {

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) throws BeansException {
        super(message, cause);
    }
}
