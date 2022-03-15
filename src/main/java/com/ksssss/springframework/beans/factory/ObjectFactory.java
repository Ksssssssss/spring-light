package com.ksssss.springframework.beans.factory;

/**
 * @author ksssss
 * @date 2022/3/13 下午10:12
 */
public interface ObjectFactory<T> {

    T getObject();
}
