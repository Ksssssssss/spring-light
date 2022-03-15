package ioc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import beans.A;
import beans.B;
import cn.hutool.core.lang.Assert;
import com.ksssss.springframework.beans.factory.support.XmlBeanFactory;
import com.ksssss.springframework.core.io.ClasspathResource;
import org.junit.jupiter.api.Test;

/**
 * @author ksssss
 * @date 2022/3/15 下午11:00
 */
public class CircularReferenceTest {

    @Test
    public void testCircularReference() {
        ClasspathResource resource = new ClasspathResource("circular-reference.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        A a = (A) beanFactory.getBean("a");
        B b = (B) beanFactory.getBean("b");
        assertEquals(a.getB(), b);
        assertEquals(a, b.getA());
    }
}
