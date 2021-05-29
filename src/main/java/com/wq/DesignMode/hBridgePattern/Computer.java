package com.wq.DesignMode.hBridgePattern;

/**
 * 电脑类 抽象的
 */
public abstract class Computer {
    // 品牌是一个属性, 把品牌组合进来 声明成protected的子类才能使用
    protected Brand brand; // 这个即可称之为桥
    public Computer(Brand brand){
        this.brand = brand;
    }

    public void computerInfo(){
        // 电脑自带的品牌信息
        brand.info();
        // 这里还只是抽象的电脑, 只有品牌信息, 还没有电脑的具体信息
    }

}
