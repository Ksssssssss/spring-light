package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.ksssss.springframework.InitializingBean;
import com.ksssss.springframework.beans.BeanWrapper;
import com.ksssss.springframework.beans.BeansException;
import com.ksssss.springframework.beans.MutablePropertyValues;
import com.ksssss.springframework.beans.PropertyValue;
import com.ksssss.springframework.beans.convert.ConversionService;
import com.ksssss.springframework.beans.factory.Aware;
import com.ksssss.springframework.beans.factory.BeanCreationException;
import com.ksssss.springframework.beans.factory.BeanFactory;
import com.ksssss.springframework.beans.factory.BeanFactoryAware;
import com.ksssss.springframework.beans.factory.BeanNameAware;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.beans.factory.config.BeanPostProcessor;
import com.ksssss.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.ksssss.springframework.beans.factory.config.RuntimeBeanReference;
import java.util.Arrays;
import java.util.List;

/**
 * @author ksssss
 * @date 2022/1/13 下午11:11
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements
        AutowireCapableBeanFactory {

    private boolean allowCircularReference = true;

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (bean != null) {
            return bean;
        }
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition bd) throws BeansException {

        BeanWrapper beanWrapper;
        beanWrapper = createBeanInstance(bd);
        final Object bean = beanWrapper != null ? beanWrapper.getWrappedInstance() : null;
        boolean earlySingletonExposure =
                bd.isSingleton() && allowCircularReference && isSingletonCurrentlyInCreation(beanName);
        if (earlySingletonExposure) {
            addSingletonFactory(beanName, () -> bean);
        }
        Object exposedObject = bean;
        populateBean(beanName, bd, beanWrapper);
        exposedObject = initializeBean(beanName, bd, bean);
        return exposedObject;
    }

    protected Object initializeBean(final String beanName, final BeanDefinition bd, final Object bean) {
        invokeAwareMethod(beanName, bean);
        Object wapperBean = bean;
        if (wapperBean != null) {
            wapperBean = applyBeanPostProcessorAfterInitialization(wapperBean, beanName);
        }
        try {
            invokeInitMethod(bd, wapperBean);
        } catch (Exception e) {
            throw new BeanCreationException(StrUtil.format("Bean:{} initMethod发生异常", beanName), e);
        }
        applyBeanPostProcessorAfterInitialization(wapperBean, beanName);
        return wapperBean;
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
                ConversionService conversionService = getConversionService();
                Class<?> sourceType = value.getClass();
                Class<?> targetType = (Class<?>) TypeUtil.getFieldType(sourceType, name);

                if (conversionService.canConvert(sourceType, targetType)) {
                    convertValue = conversionService.convert(value, targetType);
                }
            }
            Object wrappedInstance = bw.getWrappedInstance();
            ReflectUtil.setFieldValue(wrappedInstance, name, convertValue);
        }
    }

    public void setAllowCircularReference(boolean allowCircularReference) {
        this.allowCircularReference = allowCircularReference;
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition bd) {
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanName, bd);
        if (bean != null) {
            bean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor bp : beanPostProcessors) {
            Object currentBean = bp.postProcessorBeforeInitialization(existingBean, beanName);
            if (currentBean == null) {
                return result;
            }
            result = currentBean;
        }
        return result;
    }

    protected Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor bp : beanPostProcessors) {
            Object currentBean = bp.postProcessorAfterInitialization(existingBean, beanName);
            if (currentBean == null) {
                return result;
            }
            result = currentBean;
        }
        return result;
    }

    protected Object applyBeanPostProcessorBeforeInstantiation(String beanName, BeanDefinition bd) {
        List<BeanPostProcessor> beanPostProcessors = getBeanPostProcessors();
        for (BeanPostProcessor bp : beanPostProcessors) {
            if (bp instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
                Object bean = ibp.postProcessorBeforeInstantiation(bd.getClazz(), beanName);
                if (bean != null) {
                    return bean;
                }
            }
        }
        return null;
    }

    private void invokeAwareMethod(final String beanName, final Object bean) {
        if (bean instanceof Aware) {
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            } else if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
            }
        }
    }

    private void invokeInitMethod(final BeanDefinition bd, final Object bean) throws Exception {
        boolean isInitializingBean = bean instanceof InitializingBean;
        if (isInitializingBean && bd != null) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }
}
