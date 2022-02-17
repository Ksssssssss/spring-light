package com.ksssss.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 底层资源接口
 *
 * @author ksssss
 * @date 2022/1/24 下午10:51
 */
public interface Resource extends InputStreamSource {

    boolean exists();

    boolean isOpen();

    File getFile() throws IOException;

    URL getURL() throws IOException;
}
