package com.wq.DesignMode.AbstractFactoryPattern;

/**
 * 手机的抽象接口
 */
public interface phone {
    void start();
    void shutdown();
    void sendMessage();
    void callup();
}
