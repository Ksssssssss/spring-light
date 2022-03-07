package com.ksssss.springframework.beans.converter;

import com.ksssss.springframework.beans.converter.Converter;

/**
 * @author ksssss
 * @date 2022/3/4 下午2:48
 */
public abstract class AbstractConverter<T> implements Converter<T> {

    @Override
    public T convert(Object value, T defaultValue) {
        Class<T> targetType = getTargetType();
        if (targetType == null && defaultValue == null) {
            throw new NullPointerException();
        }
        if (targetType == null) {
            targetType = (Class<T>) defaultValue.getClass();
        }
        if (value == null) {
            return defaultValue;
        }
        return targetType.cast(value);
    }

    protected abstract Class<T> getTargetType();
}
