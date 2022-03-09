package com.ksssss.springframework.beans.convert;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:22
 */
public class IntegerConverter extends AbstractConverter<Integer> {

    @Override
    protected Class<Integer> getTargetType() {
        return Integer.class;
    }
}
