package com.ksssss.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.ksssss.springframework.beans.PropertyValue;
import com.ksssss.springframework.beans.factory.BeanDefinitionStoreException;
import com.ksssss.springframework.beans.factory.config.BeanDefinition;
import com.ksssss.springframework.beans.factory.config.BeanDefinitionHolder;
import com.ksssss.springframework.core.io.DefaultDocumentLoader;
import com.ksssss.springframework.core.io.DocumentLoader;
import com.ksssss.springframework.core.io.Resource;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * @author ksssss
 * @date 2022/1/24 下午11:56
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";

    public static final String ID_ATTRIBUTE = "id";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String SINGLETON_ATTRIBUTE = "singleton";


    private DocumentLoader documentLoader = new DefaultDocumentLoader();

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        try {
            Document document = documentLoader.loadDocument(resource);
            int beforeCount = getRegistry().getBeanDefinitionCount();
            Element root = document.getRootElement();
            parseBeanDefinitions(root);
            int count = getRegistry().getBeanDefinitionCount() - beforeCount;
            return count;
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("parsing XML document exception", e);
        }
    }

    public void parseBeanDefinitions(Element root) {
        for (Iterator<Node> it = root.nodeIterator(); it.hasNext(); ) {
            Node node = it.next();
            if (node instanceof Element) {
                Element ele = (Element) node;
                if (ele.getName().equals(BEAN_ELEMENT)) {
                    BeanDefinitionHolder beanDefinitionHolder = parseBeanDefinitionElement(ele);
                    getRegistry().registerBeanDefinition(beanDefinitionHolder.getBeanName(),
                            beanDefinitionHolder.getBeanDefinition());
                }
            }
        }
    }

    public BeanDefinitionHolder parseBeanDefinitionElement(Element ele) {
        String id = ele.attributeValue(ID_ATTRIBUTE);
        String nameAttr = ele.attributeValue(NAME_ATTRIBUTE);
        String beanName = id;
        if (StrUtil.isEmpty(beanName)) {
            beanName = nameAttr;
        }

        String className = ele.attributeValue(CLASS_ATTRIBUTE).trim();
        BeanDefinition bd = createBeanDefinition(className);
        parseBeanDefinitionAttributes(bd, ele);
        parsePropertyElements(bd, ele);
        return new BeanDefinitionHolder(beanName, bd);
    }

    public BeanDefinition createBeanDefinition(String className) {
        BeanDefinition bd = new BeanDefinition();
        Class<?> clazz = ClassUtil.loadClass(className);
        bd.setClazz(clazz);
        return bd;
    }

    public void parseBeanDefinitionAttributes(BeanDefinition bd, Element ele) {
        String scope = ele.attributeValue(SCOPE_ATTRIBUTE);
        String singleton = ele.attributeValue(SINGLETON_ATTRIBUTE);
        if (StrUtil.isNotEmpty(scope)) {
            bd.setScope(scope);
        } else if (StrUtil.isNotEmpty(singleton)) {
            bd.setScope(singleton);
        }
    }

    public void parsePropertyElements(BeanDefinition bd, Element ele) {
        List<Element> elements = ele.elements(PROPERTY_ELEMENT);
        for (Element property : elements) {
            String propertyName = property.attributeValue(NAME_ATTRIBUTE);
            String propertyValue = property.attributeValue(VALUE_ATTRIBUTE);
            PropertyValue pv = new PropertyValue(propertyName, propertyValue);
            bd.getPropertyValues().addPropertyValue(pv);
        }
    }
}
