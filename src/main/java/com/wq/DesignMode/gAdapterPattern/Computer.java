package com.wq.DesignMode.gAdapterPattern;

/**
 * 模拟电脑
 */
public class Computer {
    // 上网的具体实现 找一个转接头传入
    public void net(NetToUsb netToUsb){
        netToUsb.changeUSBToNet();
    }

    public static void main(String[] args) {
        // 方式一:
        // 1.需要一台电脑
        Computer c = new Computer();
        // 2.适配器
        Adapter a = new Adapter();
        // 3.网线
        WireLine w = new WireLine();
        // 这里网线并没有起到显式的作用
        c.net(a);

        // 方式二: 适配器可以动态的适配不同的网线 相当于适配器的一端先接上网线
        Adapter2 a2 = new Adapter2(w);
        // 适配器的另一端接上电脑
        c.net(a2);

    }
}
