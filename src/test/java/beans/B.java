package beans;

import lombok.Data;

/**
 * @author ksssss
 * @date 2022/3/7 下午1:09
 */
@Data
public class B {

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
