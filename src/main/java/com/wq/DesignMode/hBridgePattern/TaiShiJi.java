package com.wq.DesignMode.hBridgePattern;

public class TaiShiJi extends Computer{

    public TaiShiJi(Brand brand) {
        super(brand);
    }
    @Override
    public void computerInfo(){
        // 电脑抽象类包含的品牌信息
        super.computerInfo();
        // 这里才有具体的电脑信息
        System.out.println("台式机");
    }
}
