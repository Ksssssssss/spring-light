package com.ksssss.springframework.core.io;

/**
 * 资源加载器
 *
 * @author ksssss
 * @date 2022/1/24 下午10:45
 */
public interface ResourceLoader {

    Resource getResource(String location);
}
