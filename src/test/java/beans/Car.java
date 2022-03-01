package beans;

import lombok.Data;

/**
 * @author ksssss
 * @date 2022/2/22 上午12:21
 */
public class Car {

    private String brand;
    private Integer price;
    private String color;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
