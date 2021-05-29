package com.wq.AnnotationAndReflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获得类的运行时结构
 */
public class ReflectTest04 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class c1 = Class.forName("com.wq.AnnotationAndReflection.User");
        // 1.获得类的名字
        System.out.println(c1.getName());       // 类的全限定名
        System.out.println(c1.getSimpleName()); // 单纯的就类名
        // 2.获得类的属性
        for(Field f:c1.getFields()){            // 只能获得public的属性
            System.out.println(f);
        }
        for(Field f:c1.getDeclaredFields()){    // 可以获得所有的属性
            System.out.println(f);
        }
        System.out.println(c1.getDeclaredField("name")); // 通过指定的属性名来获取属性
        // 3.获得类的方法
        for(Method method:c1.getMethods()){     // 获得本类及父类的public方法
            System.out.println(method);
        }
        System.out.println("============================");
        for(Method method:c1.getDeclaredMethods()){     // 只获得本类的所有方法 包括private
            System.out.println(method);
        }
        // 获得指定方法                                     需要指定方法的参数类型 这样在方法被重载的情况下也可以准确的取出
        //System.out.println(c1.getMethod("getName", null));
        System.out.println(c1.getMethod("setName", String.class));

        // 获得指定的构造函数(构造器)
        for (Constructor constructor: c1.getConstructors()){ // 只能public
            System.out.println(constructor);
        }
        for (Constructor constructor: c1.getDeclaredConstructors()){ // 获取全部的
            System.out.println(constructor);
        }
        // 获得指定的构造函数(构造器)          不用传入方法名 只需要传入参数的类型即可
        System.out.println(c1.getConstructor(String.class, int.class,int.class));
    }

}
