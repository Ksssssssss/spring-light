package com.ksssss.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ksssss
 * @date 2022/1/24 下午10:58
 */
public abstract class AbstractResource implements Resource {

    @Override
    public boolean exists() {
        try {
            return getFile().exists();
        } catch (IOException ex) {
            try {
                InputStream is = getInputStream();
                is.close();
                return true;
            } catch (IOException ex2) {
                return false;
            }
        }
    }

    @Override
    public boolean isOpen() {
        return false;
    }
}
