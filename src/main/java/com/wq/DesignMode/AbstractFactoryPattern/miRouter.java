package com.wq.DesignMode.AbstractFactoryPattern;

/**
 * 小米的路由器
 */
public class miRouter implements Router{
    @Override
    public void start() {
        System.out.println("小米路由器开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米路由器关机");
    }

    @Override
    public void turnOnWIFI() {
        System.out.println("小米路由器开启WIFI");
    }

    @Override
    public void setting() {
        System.out.println("小米路由器设置参数");
    }
}
