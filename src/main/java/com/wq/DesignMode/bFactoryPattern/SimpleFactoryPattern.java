package com.wq.DesignMode.bFactoryPattern;

/**
 * 简单工厂模式/静态工厂模式
 * 汽车工厂 创建者
 * 调用者用Consumer.java类
 *
 * 方法一问题分析: 当出现了新的车类型, 就需要修改工厂的代码了
 * 这样不满足开闭原则
 *
 */

public class SimpleFactoryPattern {
    // 方法一
    public static Car getCar(String name){
        if ("五菱".equals(name)){
            return new Wuling();
        }else if("特斯拉".equals(name)){
            return new Tesla();
        }else{
            return null;
        }
    }

    // 方法二

}
