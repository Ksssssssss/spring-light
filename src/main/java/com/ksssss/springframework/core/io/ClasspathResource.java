package com.ksssss.springframework.core.io;

import cn.hutool.core.lang.Assert;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;

/**
 * path必须是相对路径
 *
 * @author ksssss
 * @date 2022/1/24 下午10:59
 */
public class ClasspathResource extends AbstractResource {

    private final String path;
    private Class clazz;

    public ClasspathResource(String path) {
        Assert.notEmpty(path);
        this.path = path;
    }

    public ClasspathResource(String path, Class clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = null;
        if (this.clazz != null) {
            is = this.clazz.getResourceAsStream(path);
        } else {
            is = this.getClass().getResourceAsStream(path);
        }
        return is;
    }

    @Override
    public File getFile() throws IOException {
        URL url = getURL();
        return new File(URLDecoder.decode(url.getFile()));
    }

    @Override
    public URL getURL() throws IOException {
        if (this.clazz != null) {
            return this.clazz.getResource(this.path);
        }
        return this.getClass().getResource(this.path);
    }
}
