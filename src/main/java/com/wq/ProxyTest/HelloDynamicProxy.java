package com.wq.ProxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloDynamicProxy implements InvocationHandler {

    private Object obj;
    public HelloDynamicProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这个代理类无论调用什么方法，都会从这里进入。");

        System.out.println("并根据方法的不同调用不同的代理逻辑");
        if(method.getName().equals("sayHello")){
            System.out.println("不同的接口在执行sayHello方法之前都先执行代理对象的附加操作");
            method.invoke(this.obj, args);

        }else if(method.getName().equals("sayGoodbye")){
            method.invoke(this.obj, args);
            System.out.println("HHHHHHHH");
        }
        return null;
    }
}
