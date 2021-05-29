package com.wq.ProxyTest;

public class HelloStaticProxy implements HelloInterface {
    private HelloInterface helloInterface = new Hello();
    @Override
    public void sayHello() {
        System.out.println("代理类的 Before invoke sayHello" );
        helloInterface.sayHello();
        System.out.println("代理类的 After invoke sayHello");
    }
    @Override
    public void sayGoodbye() {
        System.out.println("代理类的 Before invoke sayHello" );
        helloInterface.sayGoodbye();
        System.out.println("代理类的 After invoke sayHello");
    }
}
