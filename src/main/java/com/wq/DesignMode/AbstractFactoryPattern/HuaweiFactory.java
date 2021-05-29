package com.wq.DesignMode.AbstractFactoryPattern;

public class HuaweiFactory implements AbstractProductFactory {
    @Override
    public phone getPhone() {
        return new HonorPhone();
    }

    @Override
    public Router getRouter() {
        return new HonorRouter();
    }
}
