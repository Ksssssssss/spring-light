package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.convert.ConversionService;
import com.ksssss.springframework.beans.convert.ConverterRegister;
import com.ksssss.springframework.beans.factory.BeanCreationException;
import com.ksssss.springframework.beans.factory.BeanFactory;
import com.ksssss.springframework.beans.factory.BeanIsNotFactoryBeanException;
import com.ksssss.springframework.beans.factory.FactoryBean;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.beans.factory.config.BeanPostProcessor;
import com.ksssss.springframework.beans.factory.config.ConfigurableBeanFactory;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 提供bean
 *
 * @author ksssss
 * @date 2022/1/13 下午1:39
 */
public abstract class AbstractBeanFactory extends AbstractFactoryBeanSupport implements ConfigurableBeanFactory {

    private ConversionService conversionService;
    private List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        String beanName = transformName(name);
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return getObjectForBeanInstance(singleton, name, beanName, null);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        singleton = getSingleton(beanName, () -> createBean(beanName, beanDefinition));
        return getObjectForBeanInstance(singleton, name, beanName, null);
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String name, String beanName, BeanDefinition bd)
            throws BeansException {
        // 如果name是&符号开头，但不是FactoryBean，非法
        if (name.startsWith(FACTORY_BEAN_PREFIX) && !(beanInstance instanceof FactoryBean)) {
            throw new BeanIsNotFactoryBeanException(transformName(name), beanInstance.getClass());
        }
        // 获取bean或者FactoryBean实例(需要传&符号获取)
        if (!(beanInstance instanceof FactoryBean) || name.startsWith(FACTORY_BEAN_PREFIX)) {
            return beanInstance;
        }

        //获取工厂getObject返回的实例
        Object object = null;
        object = getCacheObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            if (containsBeanDefinition(beanName)) {
                object = getObjectFormFactoryBean(factoryBean, beanName);
            }
        }
        return object;
    }

    protected Object getObjectFormFactoryBean(FactoryBean<?> factoryBean, String beanName)
            throws BeanCreationException {
        Object object;
        try {
            if (factoryBean.isSingleton()) {
                object = getCacheObjectForFactoryBean(beanName);
                if (object == null) {
                    object = factoryBean.getObject();
                    putFactoryBeanObject(beanName, object);
                }
            } else {
                object = factoryBean.getObject();
            }
        } catch (Exception e) {
            String message = StrUtil.format("无法创建FactoryBean&&getObject的实例,beanName :{}", beanName);
            throw new BeanCreationException(message);
        }
        return object;
    }

    protected String transformName(String name) {
        String beanName = name;
        if (StrUtil.startWith(name, FACTORY_BEAN_PREFIX)) {
            beanName = StrUtil.removePrefix(name, FACTORY_BEAN_PREFIX);
        }
        return beanName;
    }

    @Override
    public ConversionService getConversionService() {
        return this.conversionService;
    }

    @Override
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        Assert.notNull(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
