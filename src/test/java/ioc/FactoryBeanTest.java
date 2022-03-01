package ioc;

import com.ksssss.springframework.beans.factory.support.XmlBeanFactory;
import com.ksssss.springframework.core.io.ClasspathResource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @author ksssss
 * @date 2022/3/1 下午11:02
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        ClasspathResource resource = new ClasspathResource("factory-bean.xml");
        XmlBeanFactory beanFactory = new XmlBeanFactory(resource);
        Object car = beanFactory.getBean("car");
        assertNotNull(car);
    }

}
