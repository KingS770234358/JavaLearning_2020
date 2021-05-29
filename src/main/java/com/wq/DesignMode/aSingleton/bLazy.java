package com.wq.DesignMode.aSingleton;

/**
 * 02 懒汉式单例 调用效率不高 延时加载
 * 懒汉式使用synchronized, 需要优化
 */
public class bLazy {
    // 1.私有化构造器
    private bLazy(){
    }
    // 2.类初始化的时候, 不立即加载该对象
    private static bLazy lazy;

    // 3.提供获取该对象的方法, 这里防止多线程进入, 造成判断错误需要使用synchronized, 效率较低
    public static synchronized bLazy getLazy(){
        // 当对象为空, 对象还没有被创建的时候才创建
        if(lazy==null){
            lazy = new bLazy();
        }
        return lazy;
    }
}
class bLazyTest{
    public static void main(String[] args) {
        bLazy h1 = bLazy.getLazy();
        bLazy h2 = bLazy.getLazy();
        System.out.println(h1.hashCode()==h2.hashCode()); // ===>true
    }
}