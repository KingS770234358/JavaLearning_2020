package com.wq.AnnotationAndReflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 * 测试反射的性能
 * 这里以调用user对象的getName方法为例
 */
public class ReflectTest06 {
    public static void commonMethod(){
        User u = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            u.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费:"+(endTime-startTime)+"ms");
    }
    public static void reflectInvoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u = new User();
        Class c1 = u.getClass();
        Method getName = c1.getDeclaredMethod("getName",null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(u,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费:"+(endTime-startTime)+"ms");
    }
    public static void reflectInvokeAccessible() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User u = new User();
        Class c1 = u.getClass();
        Method getName = c1.getDeclaredMethod("getName",null);
        getName.setAccessible(true);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(u,null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗费:"+(endTime-startTime)+"ms");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        commonMethod();
        reflectInvoke();
        reflectInvokeAccessible();
    }
}
