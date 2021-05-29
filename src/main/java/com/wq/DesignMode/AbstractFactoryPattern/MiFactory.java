package com.wq.DesignMode.AbstractFactoryPattern;

public class MiFactory implements AbstractProductFactory {
    @Override
    public phone getPhone() {
        return new miPhone();
    }

    @Override
    public Router getRouter() {
        return new miRouter();
    }
}
