package ioc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import beans.A;
import beans.B;
import com.ksssss.springframework.beans.factory.support.XmlBeanFactory;
import com.ksssss.springframework.core.io.ClasspathResource;
import org.junit.jupiter.api.Test;

/**
 * @author ksssss
 * @date 2022/3/1 下午11:02
 */
public class PopulateBeanTest {

    @Test
    public void testPopulateBean() {
        ClasspathResource resource = new ClasspathResource("populate-bean.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        A a = (A) beanFactory.getBean("a");
        assertNotNull(a);
        assertNotNull(a.getInfo());
        String infoA = a.getInfo();
        assertEquals("testA", infoA);

        assertNotNull(a.getB());
        B b = a.getB();
        assertNotNull(b.getInfo());
        String infoB = b.getInfo();
        assertEquals("testB", infoB);

    }

}
