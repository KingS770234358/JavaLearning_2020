package com.wq.DesignMode.aSingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 04 饿汉式改进: 静态内部类式
 * 通过反射机制获取构造器, 然后setAccessible(true)
 * 可以很容易得把私有化的构造函数多次使用
 */
public class dHungryPlus {
    // 1.私有化构造器
    private dHungryPlus(){
    }
    // 2.静态内部类
    private static class innerClass{
        // 这里static保证了内存中只有一个该对象
        // final保证了该变量是常量, 不可变
        private static final dHungryPlus dhungryplus = new dHungryPlus();
    }
    // 3.获得该对象的方法
    public static dHungryPlus getdHungryPlus(){
        return innerClass.dhungryplus;
    }
}
class ddHungryPlusTest{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        dHungryPlus h1 = dHungryPlus.getdHungryPlus();
        dHungryPlus h2 = dHungryPlus.getdHungryPlus();
        System.out.println(h1.hashCode()==h2.hashCode()); // ===>true
        // 存在问题: 反射机制破坏单例
        Constructor constructor = h1.getClass().getDeclaredConstructor(null);
        constructor.setAccessible(true);
        dHungryPlus h3 =(dHungryPlus)constructor.newInstance();
        System.out.println(h1.hashCode()==h3.hashCode());

    }
}