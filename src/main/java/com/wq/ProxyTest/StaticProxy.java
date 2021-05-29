package com.wq.ProxyTest;

public class StaticProxy {
    public static void main(String[] args) {
        HelloInterface hello = new HelloStaticProxy();
        hello.sayHello();
    }
}
