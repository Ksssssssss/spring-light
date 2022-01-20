package com.ksssss.springframework.beans;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author ksssss
 * @date 2022/1/11 下午11:57
 */
@EqualsAndHashCode
@ToString
public class MutablePropertyValues implements PropertyValues {

    private List<PropertyValue> propertyValues;

    public MutablePropertyValues() {
        this.propertyValues = new ArrayList<>();
    }

    // 深拷贝
    public MutablePropertyValues(PropertyValues other) {
        this();
        if (other != null) {
            PropertyValue[] pvs = other.getPropertyValues();
            this.propertyValues = Lists.newArrayListWithCapacity(pvs.length);
            for (PropertyValue pv : pvs) {
                addPropertyValue(new PropertyValue(pv.getName(), pv.getValue()));
            }
        }
    }

    public void addPropertyValue(PropertyValue pv) {
        for (int i = 0; i < this.propertyValues.size(); i++) {
            PropertyValue currentPv = this.propertyValues.get(i);
            if (currentPv.getName().equals(pv.getName())) {
                this.propertyValues.set(i, pv);
                return;
            }
        }
        this.propertyValues.add(pv);
    }

    @Override
    public PropertyValue[] getPropertyValues() {
        return this.propertyValues.toArray(new PropertyValue[0]);
    }

    @Override
    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue propertyValue : this.propertyValues) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }

    @Override
    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }
}
