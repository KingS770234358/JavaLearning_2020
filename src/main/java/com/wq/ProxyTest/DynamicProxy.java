package com.wq.ProxyTest;

import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {

        HelloInterface hello = new Hello(); // 被代理的类1
        // I.代理逻辑
        HelloDynamicProxy helloHandler = new HelloDynamicProxy(hello);
        // 代理类1
        HelloInterface helloDynamicProxy = (HelloInterface) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(), // 被代理对象的类加载器
                hello.getClass().getInterfaces(), // II.被代理类的所有接口实现方法 这里把方法信息注册到代理类里面了
                helloHandler); // III.  II中被代理的所有接口方法 与 定义的调用句柄 关联起来了
        // 代理类1 调用代理逻辑
        helloDynamicProxy.sayHello();
        helloDynamicProxy.sayGoodbye();

        GanFanInterface ganfan = new GanFan(); // 被代理的类2
        // I.代理逻辑
        HelloDynamicProxy ganfanHandler = new HelloDynamicProxy(ganfan);
        // 代理类2
        GanFanInterface ganfanDynamicProxy = (GanFanInterface) Proxy.newProxyInstance(
                ganfan.getClass().getClassLoader(), // 被代理对象的类加载器
                ganfan.getClass().getInterfaces(), // II.被代理类的所有接口实现方法 这里把方法信息注册到代理类里面了
                ganfanHandler); // III.  II中被代理的所有接口方法 与 定义的调用句柄 关联起来了
        // 代理类2 调用代理逻辑
        ganfanDynamicProxy.sayHello();
        ganfanDynamicProxy.sayGoodbye();
    }
}
