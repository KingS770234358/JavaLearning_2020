代理模式
静态代理: 接口I, 类A实现了接口I, 
         现在B要代理A, 则B也要实现接口I, 在B中调用A的方法, 同时附加上B自己的操作
动态代理: 实现InvocationHandler接口
public Object getProxy(){
    return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                                  要代理的接口实现类.getClass().getInterfaces(), this[InvocationHandler的实现类]);
}
public Object invoke(Object proxy,Method method, Object[] args) throw 异常{
    [动态代理的本质就是使用反射机制]
    代理类的附加操作...
    Object result = method.invoke(rent, args);
    代理类的附加操作...
    return result;
}