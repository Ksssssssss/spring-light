package com.ksssss.springframework.beans.converter;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * converter容器
 *
 * @author ksssss
 * @date 2022/3/4 下午3:31
 */
public class ConverterRegister {

    private static final Map<Type, Converter<?>> DEFAULT_CONVERTER_MAP = new ConcurrentHashMap<>();

    private ConverterRegister() {
        defaultConverters();
    }

    private static class SingleInstance {

        private static final ConverterRegister INSTANCE = new ConverterRegister();
    }

    public static ConverterRegister getInstance() {
        return SingleInstance.INSTANCE;
    }

    private void defaultConverters() {
        DEFAULT_CONVERTER_MAP.put(Number.class, new NumberConverter());
        DEFAULT_CONVERTER_MAP.put(Byte.class, new ByteConverter());
        DEFAULT_CONVERTER_MAP.put(Short.class, new ShortConverter());
        DEFAULT_CONVERTER_MAP.put(Integer.class, new IntegerConverter());
        DEFAULT_CONVERTER_MAP.put(Long.class, new LongConverter());
        DEFAULT_CONVERTER_MAP.put(Float.class, new FloatConverter());
        DEFAULT_CONVERTER_MAP.put(Double.class, new DoubleConverter());
        DEFAULT_CONVERTER_MAP.put(Boolean.class, new BooleanConverter());
        DEFAULT_CONVERTER_MAP.put(String.class, new StringConverter());
    }

    public Converter<?> getConverter(Type type) {
        return DEFAULT_CONVERTER_MAP.get(type);
    }
}
