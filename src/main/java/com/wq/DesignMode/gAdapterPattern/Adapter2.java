package com.wq.DesignMode.gAdapterPattern;

/**
 * 通过组合的方式得到网线
 */
public class Adapter2 implements NetToUsb{
    private WireLine w;
    // 通过构造函数获得适配器
    public Adapter2(WireLine w){
        this.w = w;
    }    @Override
    public void changeUSBToNet() {
        System.out.println("组合方式");
        w.request(); // 可以上网了
    }
}
