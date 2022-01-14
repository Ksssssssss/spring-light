package com.ksssss.springframework.beans;

/**
 * @author ksssss
 * @date 2022/1/12 下午10:57
 */
public abstract class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) throws BeansException {
        super(message, cause);
    }
}
