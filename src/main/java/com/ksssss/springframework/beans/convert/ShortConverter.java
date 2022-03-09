package com.ksssss.springframework.beans.convert;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:25
 */
public class ShortConverter extends AbstractConverter<Short> {
    @Override
    protected Class<Short> getTargetType() {
        return Short.class;
    }
}
