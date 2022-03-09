package com.ksssss.springframework.beans.convert;

/**
 * 类型转换器
 *
 * @author ksssss
 * @date 2022/3/4 下午2:45
 */
public interface Converter<T> {

    T convert(Object value, T defaultValue);
}
