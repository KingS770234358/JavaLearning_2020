package com.wq.DesignMode.eBuilderPattern;

/**
 * 指挥者类Director接收一个建造者对象(实现类工人Worker对象)
 * 调用worker对象的方法,
 * [按照一定顺序]完成产品各个部分的制作
 * 最后调用work对象的返回产品方法返回产品
 */
public class Director {
    public Product build(Builder builer){
        builer.buildA();
        builer.buildB();
        builer.buildC();
        builer.buildD();
        return builer.getProduct();
    }
}
