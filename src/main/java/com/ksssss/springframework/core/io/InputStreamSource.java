package com.ksssss.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ksssss
 * @date 2022/1/24 下午10:53
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;
}
