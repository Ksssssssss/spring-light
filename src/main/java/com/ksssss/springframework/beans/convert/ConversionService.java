package com.ksssss.springframework.beans.convert;

import cn.hutool.core.lang.Assert;

/**
 * @author ksssss
 * @date 2022/3/9 下午10:30
 */
public class ConversionService {

    private final ConverterRegister converterRegister = ConverterRegister.getInstance();

    public boolean canConvert(Class<?> source, Class<?> target) {
        // todo
        return true;
    }

    public <T> T convert(Object value, Class<T> targetType) {
        Assert.notNull(targetType, "targetType not allow null");
        Converter<?> converter = converterRegister.getConverter(targetType);
        if (converter != null) {
            return (T) converter.convert(value, null);
        }
        return null;
    }
}
