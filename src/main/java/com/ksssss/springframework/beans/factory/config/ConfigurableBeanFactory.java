package com.ksssss.springframework.beans.factory.config;

import cn.hutool.core.convert.ConverterRegistry;
import com.ksssss.springframework.beans.convert.ConversionService;
import com.ksssss.springframework.beans.convert.ConverterRegister;
import com.ksssss.springframework.beans.factory.BeanFactory;
import com.ksssss.springframework.beans.factory.support.SingletonBeanRegistry;
import java.util.List;

/**
 * factory配置
 *
 * @author ksssss
 * @date 2022/3/9 下午10:19
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    ConversionService getConversionService();

    void setConversionService(ConversionService conversionService);

    List<BeanPostProcessor> getBeanPostProcessors();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
