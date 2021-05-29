package com.wq.DesignMode.AbstractFactoryPattern;

/**
 * 抽象产品的抽象工厂: 生产抽象产品用的抽象工厂
 */
public interface AbstractProductFactory {
    //生产手机
    phone getPhone();
    //生产路由器
    Router getRouter();
}
