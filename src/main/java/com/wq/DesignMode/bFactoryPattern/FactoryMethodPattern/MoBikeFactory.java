package com.wq.DesignMode.bFactoryPattern.FactoryMethodPattern;

import com.wq.DesignMode.bFactoryPattern.Car;
import com.wq.DesignMode.bFactoryPattern.MoBike;

public class MoBikeFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new MoBike();
    }
}
