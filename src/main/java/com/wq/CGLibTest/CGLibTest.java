package com.wq.CGLibTest;

import net.sf.cglib.proxy.Enhancer;

public class CGLibTest {
    public static void main(String[] args) {
        Enhancer e = new Enhancer();
        // 1.指定需要生成子类的对应父类 -- 被代理的类 (这就是传入MethodInterceptor的intercept方法的obj)
        e.setSuperclass(SimplePojo.class);
        // 2.设置CallBack实现的 ！！实例！！
        e.setCallback(new SimplePojoCallBack());
        // 3.生成代理子类(这就是传入MethodInterceptor的intercept方法的proxy)
        SimplePojo spj = (SimplePojo)e.create();
        spj.cry();
        spj.eat();
    }
}
