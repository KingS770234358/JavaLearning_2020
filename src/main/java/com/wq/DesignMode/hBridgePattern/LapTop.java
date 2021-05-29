package com.wq.DesignMode.hBridgePattern;

public class LapTop extends Computer {
    public LapTop(Brand brand) {
        super(brand);
    }
    @Override
    public void computerInfo(){
        super.computerInfo();
        System.out.println("笔记本");
    }
}
