package com.wq.CGLibTest;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SimplePojoCallBack implements MethodInterceptor {
    @Override                                                   // 直接传入了代理类
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        System.out.println("这个代理类无论调用什么方法，都会从这里进入。");
        System.out.println("并根据方法的不同调用不同的代理逻辑");
        if(method.getName().equals("cry")){
            System.out.println("不同的接口在执行sayHello方法之前都先执行代理对象的附加操作");
            proxy.invokeSuper(obj, args);

        }else if(method.getName().equals("eat")){
            proxy.invokeSuper(obj, args);
            System.out.println("HHHHHHHH");
        }
        return null;
    }
}
