package com.wq.DesignMode.bFactoryPattern;

import com.wq.DesignMode.bFactoryPattern.FactoryMethodPattern.MoBikeFactory;
import com.wq.DesignMode.bFactoryPattern.FactoryMethodPattern.TeslaFactory;
import com.wq.DesignMode.bFactoryPattern.FactoryMethodPattern.WulingFactory;

import java.text.SimpleDateFormat;

public class Consumer {
    public static void main(String[] args) {
        // 1.最原始的方法需要了解接口以及所有的实现类
        Car car1 = new Wuling();
        Car car2 = new Tesla();
        car1.name();
        car2.name();

        // 2.方式一:使用统一的工厂来创建车
        Car fcar1 = SimpleFactoryPattern.getCar("五菱");
        Car fcar2 = SimpleFactoryPattern.getCar("特斯拉");
        fcar1.name();
        fcar2.name();

        // 3.方式二:上面方式在新增车型的时候 需要修改原有的汽车工厂类
        // 方式二为工厂方法模式, 给每种车都创建对应的汽车工厂实现汽车工厂接口
        Car fmCar1 = new WulingFactory().getCar();
        Car fmCar2 = new TeslaFactory().getCar();
        Car fmCar3 = new MoBikeFactory().getCar();
        fmCar1.name();
        fmCar2.name();
        fmCar3.name();

    }
}
