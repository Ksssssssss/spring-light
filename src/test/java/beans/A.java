package beans;

import lombok.Data;

/**
 * @author ksssss
 * @date 2022/3/7 下午1:08
 */
public class A {

    private B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
