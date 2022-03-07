package beans;

import lombok.Data;

/**
 * @author ksssss
 * @date 2022/3/7 下午1:08
 */
public class A {

    private B b;
    private String info;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
