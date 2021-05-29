package com.wq.DesignMode.AbstractFactoryPattern;

/**
 * 小米手机类
 */
public class miPhone implements phone {
    @Override
    public void start() {
        System.out.println("小米手机启动");
    }

    @Override
    public void shutdown() {
        System.out.println("小米手机关机");
    }

    @Override
    public void sendMessage() {
        System.out.println("小米手机发短信");
    }

    @Override
    public void callup() {
        System.out.println("小米手机打电话");
    }
}
