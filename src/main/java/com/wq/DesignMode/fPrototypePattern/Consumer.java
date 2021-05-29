package com.wq.DesignMode.fPrototypePattern;

import java.util.Date;

public class Consumer {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 1、创建原型对象
        Date d = new Date();                             // 这里使用的是日期的引用
        PrototypeClass p = new PrototypeClass("原型1", d);
        System.out.println(p);
        System.out.println(p.getCreateTime().hashCode());
        System.out.println(p.hashCode());

        // 2.克隆
        PrototypeClass pcv = (PrototypeClass) p.clone();
        System.out.println(pcv);
        System.out.println(pcv.getCreateTime().hashCode());
        System.out.println(pcv.hashCode());
        /**
         * PrototypeClass{name='原型1', createTime=Sat Feb 15 15:12:10 CST 2020}
         * 83954662
         * PrototypeClass{name='原型1', createTime=Sat Feb 15 15:12:10 CST 2020}
         * 1751075886
         * ====> 一模一样 但是不是同一个对象
         *
         *
         */
        d.setTime(13132132);
        System.out.println(p.getCreateTime());
        System.out.println(pcv.getCreateTime());

    }
}
