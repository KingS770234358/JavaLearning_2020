package com.wq.DesignMode.eBuilderPattern;

public class Consumer {
    public static void main(String[] args) {
        // 1.需要一个工人 里面包含这产品
        Worker worker = new Worker(); // 创建工人的时候调用工人的无参构造函数 就同时创建了产品
        // 2.需要一个指挥者
        Director director = new Director();
        // 3.调用指挥者 的方法 使用工人制作产品 并且返回产品
        Product p = director.build(worker);
        System.out.println(p.toString());

        // 1.创建一个工人
        Worker2 worker2 = new Worker2();
        // 2.直接使用默认的设置制作产品
        Product2 product2 = worker2.getProduct();
        System.out.println(product2);

        // 1.创建一个工人
        Worker2 worker22 = new Worker2();
        // 2.使用用户的设置制作产品
        Product2 product22 = worker2.buildA("用户自定义的A")
                .buildB("用户自定义的B")
                .buildC("用户自定义的C")
                .buildD("用户自定义的D").getProduct();
        System.out.println(product22);
        Worker2 worker222 = worker22;
        System.out.println(worker222.hashCode()==worker22.hashCode());

    }
}
