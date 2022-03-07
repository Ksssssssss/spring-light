package com.ksssss.springframework.beans.converter;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:07
 */
public class NumberConverter extends AbstractConverter<Number> {

    @Override
    protected Class<Number> getTargetType() {
        return Number.class;
    }
}
