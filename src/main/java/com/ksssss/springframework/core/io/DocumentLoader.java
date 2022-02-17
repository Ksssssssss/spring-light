package com.ksssss.springframework.core.io;

import java.io.IOException;
import org.dom4j.DocumentException;
import org.dom4j.Document;

/**
 * 资源文件转换为文档
 *
 * @author ksssss
 * @date 2022/1/25 下午11:44
 */
public interface DocumentLoader {

    Document loadDocument(Resource resource) throws DocumentException,IOException;
}
