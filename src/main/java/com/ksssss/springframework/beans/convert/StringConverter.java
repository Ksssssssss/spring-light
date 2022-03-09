package com.ksssss.springframework.beans.convert;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:25
 */
public class StringConverter extends AbstractConverter<String> {
    @Override
    protected Class<String> getTargetType() {
        return String.class;
    }
}
