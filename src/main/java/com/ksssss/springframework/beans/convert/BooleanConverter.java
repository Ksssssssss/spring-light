package com.ksssss.springframework.beans.convert;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:28
 */
public class BooleanConverter extends AbstractConverter<Boolean> {

    @Override
    protected Class<Boolean> getTargetType() {
        return Boolean.class;
    }
}
