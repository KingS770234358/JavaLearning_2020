package com.wq.DesignMode.eBuilderPattern;

/**
 * Worker继承抽象的建造者类
 * Worker创建并保存这具体的产品
 * Worker实现抽象的建造者类定义的方法 编写多个函数对自身的产品各个部分进行制作
 * Worker可以把自己的产品返回出去
 */
public class Worker extends Builder{
    private Product product;
    // 在工人的无参构造函数里创建产品
    public Worker(){
        this.product = new Product();
    }
    @Override
    void buildA() {
        product.setA("A part");
        System.out.println("BuildA Completed!");
    }

    @Override
    void buildB() {
        product.setB("B part");
        System.out.println("BuildB Completed!");
    }

    @Override
    void buildC() {
        product.setC("C part");
        System.out.println("BuildC Completed!");
    }

    @Override
    void buildD() {
        product.setD("D part");
        System.out.println("BuildD Completed!");
    }

    @Override
    Product getProduct() {
        return product;
    }
}
