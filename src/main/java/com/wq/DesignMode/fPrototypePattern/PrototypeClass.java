package com.wq.DesignMode.fPrototypePattern;

import java.util.Date;

public class PrototypeClass implements Cloneable {
    private String name;
    private Date createTime;
    @Override
    protected Object clone() throws CloneNotSupportedException{
        PrototypeClass prototypeClass = (PrototypeClass)super.clone();
        // 实现深度拷贝 对象中的属性对象也需要拷贝 把这个对象的createTime属性也拷贝出去给副本
        prototypeClass.createTime = (Date) this.createTime.clone();
        return prototypeClass;
    }

    public PrototypeClass() {
    }

    public PrototypeClass(String name, Date createTime) {
        this.name = name;
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PrototypeClass{" +
                "name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
