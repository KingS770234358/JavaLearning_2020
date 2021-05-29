package com.wq.AnnotationAndReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射 动态的构造对象 调用方法
 */
public class ReflectTest05 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获得Class对象
        Class c1 = Class.forName("com.wq.AnnotationAndReflection.User");

        // 1.1构造一个对象 ==== 本质上是调用类的无参构造器, 没有无参构造器会报错, 无参构造器的访问权限也要足够
        User user = (User) c1.newInstance();
        System.out.println(user);

        // 1.2通过获得的构造器 构造对象
        Constructor constructor = c1.getDeclaredConstructor(String.class,int.class,int.class);
        User user2 = (User) constructor.newInstance("琴江",1,18);
        System.out.println(user2);

        // 2.通过反射调用方法
        User user3 = (User) c1.newInstance();
        // 获得方法
        Method setName = c1.getDeclaredMethod("setName", String.class);
        // 调用方法的invoke方法来执行获得的方法 === 需要传入对应的 对象 以及 参数
        setName.invoke(user3, "徐庆");
        System.out.println(user3);

        // 3.通过反射操作属性
        User user4 = (User) c1.newInstance();
        // 获得指定的属性
        Field name = c1.getDeclaredField("name");
        // 开启属性可直接访问 属性本身为private修饰 不可直接访问 setAccessible 默认为false
        name.setAccessible(true);
        // 调用得到的属性的set方法 === 需要传入对应的 对象 以及 参数
        name.set(user4,"王强");
        System.out.println(user4);
    }
}
