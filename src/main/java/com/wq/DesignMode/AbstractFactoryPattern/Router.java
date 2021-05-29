package com.wq.DesignMode.AbstractFactoryPattern;

/**
 * 路由器的抽象接口
 */
public interface Router {
    void start();
    void shutdown();
    void turnOnWIFI();
    void setting();
}
