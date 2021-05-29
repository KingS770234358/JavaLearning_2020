package com.wq.DesignMode.bFactoryPattern.FactoryMethodPattern;

import com.wq.DesignMode.bFactoryPattern.Car;
import com.wq.DesignMode.bFactoryPattern.Wuling;

public class WulingFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Wuling();
    }
}
