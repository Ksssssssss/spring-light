package ioc;

import static org.junit.jupiter.api.Assertions.*;

import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * @author ksssss
 * @date 2022/1/20 下午11:47
 */
public class DefaultListableBeanFactoryTest {

    @Test
    public void testDefaultListableBeanFactoryWithBeanDefinition() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setClazz(HelloBeanDefinition.class);
        defaultListableBeanFactory.registerBeanDefinition("helloBeanDefinition", beanDefinition);
        Object helloBeanDefinition = defaultListableBeanFactory.getBean("helloBeanDefinition");
        assertNotNull(helloBeanDefinition);
        assertEquals(((HelloBeanDefinition)helloBeanDefinition).sayHello(),"Hello BeanDefinition");
    }
}

class HelloBeanDefinition {

    public String sayHello() {
        System.out.println("Hello BeanDefinition");
        return "Hello BeanDefinition";
    }
}