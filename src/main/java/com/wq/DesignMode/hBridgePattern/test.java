package com.wq.DesignMode.hBridgePattern;

public class test {
    public static void main(String[] args) {
        // 品牌和电脑类型自定义组装 灵活 节省代码
        // 苹果笔记本
        Computer appleLaptop = new LapTop(new AppleBrand());
        appleLaptop.computerInfo();
        // 联想台式机
        Computer lTaishiji = new TaiShiJi(new LenovoBrand());
        lTaishiji.computerInfo();

    }
}
