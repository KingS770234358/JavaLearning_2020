package com.wq.DesignMode.bFactoryPattern.FactoryMethodPattern;

import com.wq.DesignMode.bFactoryPattern.Car;
import com.wq.DesignMode.bFactoryPattern.Tesla;

public class TeslaFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new Tesla();
    }
}
