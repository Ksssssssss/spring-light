package com.ksssss.springframework;

/**
 * 在BeanFactory设置所有属性后做出反应：例如，执行自定义初始化，或仅检查是否已设置所有必需属性。
 *
 * @author ksssss
 * @date 2022/3/16 下午10:55
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
