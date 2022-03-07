package com.ksssss.springframework.beans.converter;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:27
 */
public class FloatConverter extends AbstractConverter<Float> {

    @Override
    protected Class<Float> getTargetType() {
        return Float.class;
    }
}
