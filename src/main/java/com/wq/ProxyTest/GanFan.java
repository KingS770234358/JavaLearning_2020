package com.wq.ProxyTest;

public class GanFan implements GanFanInterface {

    @Override
    public void sayHello() {
        System.out.println("开始干饭");
    }

    @Override
    public void sayGoodbye() {
        System.out.println("干饭完毕");
    }
}
