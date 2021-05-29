package com.wq.DesignMode.gAdapterPattern;

/**
 * 模拟真正的适配器 一端连接网线 一端连接Usb接口
 */
// 继承网线类 实现NetToUsb接口
public class Adapter extends WireLine implements NetToUsb {
    @Override
    public void changeUSBToNet() {
        super.request(); // 可以上网了
    }
}
