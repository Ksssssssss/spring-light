package com.ksssss.springframework.beans.converter;

/**
 * @author ksssss
 * @date 2022/3/4 下午3:25
 */
public class ByteConverter extends AbstractConverter<Byte> {
    @Override
    protected Class<Byte> getTargetType() {
        return Byte.class;
    }
}
