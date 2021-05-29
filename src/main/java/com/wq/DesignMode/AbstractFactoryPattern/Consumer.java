package com.wq.DesignMode.AbstractFactoryPattern;

public class Consumer {
    public static void main(String[] args) {
        System.out.println("===小米系列产品===");
        // 1.1获得小米工厂
        MiFactory miFactory = new MiFactory();
        // 1.2生产手机
        phone miphone = miFactory.getPhone();
        miphone.start();
        miphone.callup();
        miphone.sendMessage();
        miphone.shutdown();
        // 1.3生产路由器
        Router miRouter = miFactory.getRouter();
        miRouter.start();
        miRouter.setting();
        miRouter.turnOnWIFI();
        miRouter.shutdown();

        System.out.println("===华为系列产品===");
        // 2.1获得华为工厂
        HuaweiFactory hFactory = new HuaweiFactory();
        // 2.2创建华为手机
        HonorPhone hPhone = new HonorPhone();
        hPhone.start();
        hPhone.callup();
        hPhone.sendMessage();
        hPhone.shutdown();
        // 2.3创建华为路由器
        HonorRouter hRouter = new HonorRouter();
        hRouter.start();
        hRouter.setting();
        hRouter.turnOnWIFI();
        hRouter.shutdown();
    }
}
