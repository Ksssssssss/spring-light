package io;

import static org.junit.jupiter.api.Assertions.*;

import com.ksssss.springframework.core.io.ClasspathResource;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * @author ksssss
 * @date 2022/2/19 上午12:24
 */
public class ClasspathResourceTest {

    @Test
    public void testClasspathResourceLoader() throws IOException {
        ClasspathResource resource = new ClasspathResource("hello.xml");
        assertNotNull(resource.getInputStream());
    }
}