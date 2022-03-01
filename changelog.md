# 更新记录

## spring-light

> 分支 factory-bean

主要改动

- 新增FactoryBean(隐藏实例化复杂bean的细节)
    
    * 通过transformName()方法获取beanName;
    * 拿到原始的bean状态
    * 通过getObjectForBeanInstance()方法获取到最终想要的bean
  
      * getObjectForBeanInstance(beanInstance,name,beanName,bd)方法
      * 如果name是否以&符号开头并beanInstance不是FactoryBean则非法
      * 获取普通bean或FactoryBean实例
      * 获取FactoryBean的getObject实例
      * 从getObjectFromFactoryBean中获取
    

