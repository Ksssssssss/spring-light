package com.ksssss.springframework.beans.convert;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:25
 */
public class LongConverter extends AbstractConverter<Long> {
    @Override
    protected Class<Long> getTargetType() {
        return Long.class;
    }
}
