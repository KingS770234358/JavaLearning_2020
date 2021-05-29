package com.wq.DesignMode.aSingleton;

/**
 * 03 DCL懒汉式 —— Double双重检测Check锁Lock模式
 */
public class cDCLLazy {
    // 1.私有化构造器
    private cDCLLazy(){
    }
    // 2.类初始化的时候 不立即加载该类
    // 使用volatile修饰, 当这个属性被某个线程改变时, 其他线程中的该属性立即更新
    // 极大幅度避免【存在问题】
    private volatile static cDCLLazy dclLazy;
    // 3.提供获取该对象的方法, 再去掉synchronized的情况下保证线程的安全
    public static cDCLLazy getDclLazy(){
        // 当dclLazy为空的时候 线程参与实例创建的竞争
        if (dclLazy==null){
            // 使用同步代码块实现 竞争到使用权的线程 锁住该类 一次只能使用一个
            synchronized(cDCLLazy.class){
                if (dclLazy==null){
                    dclLazy = new cDCLLazy(); // 【存在问题】
                }
            }
        }
        return dclLazy;
    }

}
class cDCLLazyTest{
    public static void main(String[] args) {
        cDCLLazy h1 = cDCLLazy.getDclLazy();
        cDCLLazy h2 = cDCLLazy.getDclLazy();
        System.out.println(h1.hashCode()==h2.hashCode()); // ===>true
    }
}