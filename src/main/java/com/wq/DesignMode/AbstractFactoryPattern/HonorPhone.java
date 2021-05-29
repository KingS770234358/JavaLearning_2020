package com.wq.DesignMode.AbstractFactoryPattern;

/**
 * 华为手机
 */
public class HonorPhone implements phone{
    @Override
    public void start() {
        System.out.println("华为手机开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关机");
    }

    @Override
    public void sendMessage() {
        System.out.println("华为手机发送短信");
    }

    @Override
    public void callup() {
        System.out.println("华为手机打电话");
    }
}
