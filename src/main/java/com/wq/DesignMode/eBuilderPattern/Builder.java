package com.wq.DesignMode.eBuilderPattern;

/**
 * Builder抽象类
 */
public abstract class Builder {
    abstract void buildA();  // 制作产品的A部分
    abstract void buildB();  // 制作产品的B部分
    abstract void buildC();  // 制作产品的C部分
    abstract void buildD();  // 制作产品的D部分
    abstract Product getProduct(); // 获得制作好的产品
}
