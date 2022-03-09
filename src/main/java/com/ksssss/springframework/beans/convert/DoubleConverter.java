package com.ksssss.springframework.beans.convert;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:27
 */
public class DoubleConverter extends AbstractConverter<Double> {

    @Override
    protected Class<Double> getTargetType() {
        return Double.class;
    }
}
