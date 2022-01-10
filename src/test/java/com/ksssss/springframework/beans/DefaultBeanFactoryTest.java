package com.ksssss.springframework.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * @author ksssss
 * @date 2022/1/10 下午11:19
 */
public class DefaultBeanFactoryTest {

    @Test
    public void testSimpleBeanFactory() {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();
        HelloWorld helloWorld = new HelloWorld();
        beanFactory.registerBean("helloWorld", helloWorld);
        HelloWorld bean = (HelloWorld) beanFactory.getBean("helloWorld");
        assertNotNull(bean);
        assertEquals(bean.sayHello(), "Hello World");
    }
}

class HelloWorld {

    public String sayHello() {
        System.out.println("Hello World");
        return "Hello World";
    }
}