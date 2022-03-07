package com.ksssss.springframework.beans.converter;

/**
 * @author ksssss
 * @date 2022/3/4 下午5:42
 */
public class Convert {

    public static <T> T convert(Class<T> type, Object value) {
        return (T) ConverterRegister.getInstance().getConverter(type).convert(value, null);
    }
}
