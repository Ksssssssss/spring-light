package ioc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import beans.Person;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.beans.factory.support.XmlBeanFactory;
import com.ksssss.springframework.core.io.ClasspathResource;
import org.junit.jupiter.api.Test;

/**
 * @author ksssss
 * @date 2022/2/19 上午12:12
 */
public class XmlBeanFactoryTest {

    @Test
    public void testXmlBeanFactory() {
        ClasspathResource resource = new ClasspathResource("hello.xml");
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory(resource);
        BeanDefinition bd = xmlBeanFactory.getBeanDefinition("person");
        assertEquals(bd.getClazz(), Person.class);
    }

}