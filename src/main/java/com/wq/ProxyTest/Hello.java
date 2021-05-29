package com.wq.ProxyTest;

public class Hello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello");
    }
    @Override
    public void sayGoodbye(){
        System.out.println("Goodbye");
    }
}
