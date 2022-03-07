package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.util.ReferenceUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.ksssss.springframework.beans.BeanWrapper;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.MutablePropertyValues;
import com.ksssss.springframework.beans.PropertyValue;
import com.ksssss.springframework.beans.converter.Convert;
import com.ksssss.springframework.beans.factory.BeanCreationException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.beans.factory.config.RuntimeBeanReference;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ksssss
 * @date 2022/1/13 下午11:11
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements
        AutowireCapableBeanFactory {

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) throws BeansException {

        BeanWrapper beanWrapper;
        beanWrapper = createBeanInstance(beanDefinition);
        final Object bean = beanWrapper != null ? beanWrapper.getWrappedInstance() : null;

        populateBean(beanName, beanDefinition, beanWrapper);
        registerSingleton(beanName, bean);
        return bean;
    }

    protected BeanWrapper createBeanInstance(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object beanInstance = clazz.newInstance();
            BeanWrapper beanWrapper = new BeanWrapper();
            beanWrapper.setWrappedClass(clazz);
            beanWrapper.setWrappedInstance(beanInstance);
            return beanWrapper;
        } catch (InstantiationException e) {
            throw new BeanCreationException("无法实例化类 ’" + clazz.getCanonicalName() + "' 可能是由于该类抽象类、接口、无默认构造函数", e);
        } catch (IllegalAccessException e) {
            throw new BeanCreationException("无法实例化类 ’" + clazz.getCanonicalName() + "' 可能是由于无权访问指定类", e);
        }
    }

    protected void populateBean(String beanName, BeanDefinition bd, BeanWrapper bw) {
        MutablePropertyValues pvs = bd.getPropertyValues();
        if (bw == null) {
            if (pvs != null) {
                throw new BeanCreationException(StrUtil.format("无法为null bean注入属性，beanName:{}", beanName));
            } else {
                return;
            }
        }
        applyPropertyValues(beanName, bw, pvs);
    }

    protected void applyPropertyValues(String beanName, BeanWrapper bw, MutablePropertyValues pvs) {
        if (pvs.isEmpty()) {
            return;
        }
        List<PropertyValue> newPvs = Arrays.asList(pvs.getPropertyValues());
        for (PropertyValue pv : newPvs) {
            String name = pv.getName();
            Object value = pv.getValue();
            Object convertValue = null;
            if (value instanceof RuntimeBeanReference) {
                if (containsBeanDefinition(name)) {
                    convertValue = getBean(name);
                }
            } else {
                convertValue = Convert.convert(value.getClass(),value);
            }
            Object wrappedInstance = bw.getWrappedInstance();
            ReflectUtil.setFieldValue(wrappedInstance, name, convertValue);
        }
    }
}
