package com.wq.DesignMode.aSingleton;

/**
 * 01 懒汉式单例模式 调用效率高 不延时加载
 * 存在缺点: 当类占用大量内存, 而使用频率低的时候
 * 会造成内存空间占用的浪费
 */
public class aHungry {
    // 1. 私有化该类的构造器
    private aHungry(){
    }
    // 2. 类初始化的时候, 立即加载该类的对象
    private static aHungry hungry = new aHungry();
    // 3. 提供获取该对象的方法, 不使用Syncronized, 效率高
    public static aHungry getHungry(){
        return hungry;
    }
}
class aHungryTest{
    public static void main(String[] args) {
        aHungry h1 = aHungry.getHungry();
        aHungry h2 = aHungry.getHungry();
        System.out.println(h1.hashCode()==h2.hashCode()); // ===>true
    }
}
