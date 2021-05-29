package com.wq.DesignMode.eBuilderPattern;

public class Worker2 extends Builder2{
    private Product2 product;
    // 在工人的无参构造函数里创建产品
    public Worker2(){
        this.product = new Product2();
    }
    @Override
    Builder2 buildA(String msg) {
        product.setA(msg);
        System.out.println("BuildA Completed!");
        return this;
    }

    @Override
    Builder2 buildB(String msg) {
        product.setB(msg);
        System.out.println("BuildB Completed!");
        return this;
    }

    @Override
    Builder2 buildC(String msg) {
        product.setC(msg);
        System.out.println("BuildC Completed!");
        return this;
    }

    @Override
    Builder2 buildD(String msg) {
        product.setD(msg);
        System.out.println("BuildD Completed!");
        return this;
    }

    @Override
    Product2 getProduct() {
        return product;
    }
}
