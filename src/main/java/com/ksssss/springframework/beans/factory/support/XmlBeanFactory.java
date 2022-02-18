package com.ksssss.springframework.beans.factory.support;

import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.core.io.Resource;

/**
 * @author ksssss
 * @date 2022/1/25 下午11:25
 */
public class XmlBeanFactory extends DefaultListableBeanFactory {

    private BeanDefinitionReader reader = new XmlBeanDefinitionReader(this);

    public XmlBeanFactory(Resource resource) throws BeansException {
        this.reader.loadBeanDefinitions(resource);
    }
}
