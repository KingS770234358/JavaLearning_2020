package com.wq.DesignMode.AbstractFactoryPattern;

public class HonorRouter implements Router{
    @Override
    public void start() {
        System.out.println("华为路由器开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为路由器关机");
    }

    @Override
    public void turnOnWIFI() {
        System.out.println("华为路由器开启WIFI");
    }

    @Override
    public void setting() {
        System.out.println("华为路由器设置参数");
    }
}
