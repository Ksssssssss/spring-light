package com.ksssss.springframework.beans.factory.support;

import com.ksssss.springframework.beans.factory.BeanDefinitionStoreException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.core.io.Resource;

/**
 * 将Resource对象转换为BeanDefinition
 *
 * @author ksssss
 * @date 2022/1/24 下午10:41
 */
public interface BeanDefinitionReader {

    int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;
}
