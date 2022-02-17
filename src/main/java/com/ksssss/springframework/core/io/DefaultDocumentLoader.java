package com.ksssss.springframework.core.io;

import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @author ksssss
 * @date 2022/1/25 下午11:48
 */
public class DefaultDocumentLoader implements DocumentLoader {
    @Override
    public Document loadDocument(Resource resource) throws DocumentException, IOException {
        SAXReader saxReader = new SAXReader();
        return saxReader.read(resource.getInputStream());
    }
}
