package com.wq.DesignMode.eBuilderPattern;

public abstract class Builder2 {
    abstract Builder2 buildA(String msg);  // 制作产品的A部分
    abstract Builder2 buildB(String msg);  // 制作产品的B部分
    abstract Builder2 buildC(String msg);  // 制作产品的C部分
    abstract Builder2 buildD(String msg);  // 制作产品的D部分
    abstract Product2 getProduct(); // 获得制作好的产品
}
