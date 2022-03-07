package beans;

import cn.hutool.core.util.StrUtil;
import com.ksssss.springframework.beans.factory.FactoryBean;
import java.util.List;

/**
 * @author ksssss
 * @date 2022/3/1 下午10:56
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        String[] infos = this.carInfo.split(StrUtil.COMMA);
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setColor(infos[1]);
        car.setPrice(Integer.parseInt(infos[2]));
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
