package com.wq.DesignMode.aSingleton;

/**
 * 使用枚举类的方式来获取单例 最安全也是最简单快速的方法
 * 线程安全, 调用效率高, 不能延时
 */

public enum eEnumPowerful {
    INSTANCE,INSTANCE2;
    public eEnumPowerful getInstance(){
        return INSTANCE;
    }
    public eEnumPowerful getInstance2(){
        return INSTANCE2;
    }
    String name = "很好";
    public String getName(){
        return this.name;
    }
}
class eEnumPowerfulTest{
    public static void main(String[] args) {
        eEnumPowerful e1 = eEnumPowerful.INSTANCE;
        eEnumPowerful e2 = eEnumPowerful.INSTANCE;
        eEnumPowerful e3= eEnumPowerful.INSTANCE2;
        System.out.println(e1.name);
        System.out.println(e1.getName());
        System.out.println(e1.getName().hashCode()==e2.getName().hashCode());
        System.out.println(e1.hashCode()==e2.hashCode());

        System.out.println(e3);
        System.out.println(e1.hashCode()==e3.hashCode());
    }
}
