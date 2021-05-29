package com.wq.AnnotationAndReflection;

public class ReflectTest02 {
    static {
        System.out.println("main函数所在的类被加载");
    }
    public static void main(String[] args) throws ClassNotFoundException {
        //1.1主动引用
        Son son = new Son();
        System.out.println("=============================");
        //1.2反射也会产生主动引用 只会加载一次 如果不注释掉1.1就看不到效果
        Class.forName("com.wq.AnnotationAndReflection.Son");
        System.out.println("=============================");
        //2.1不会类初始化的方法 只会加载一次 如果不注释掉1.1和1.2就看不到效果
        // 子类直接调用父类的静态变量不会进行子类的初始化
        System.out.println(Son.b);
        System.out.println("=============================");
        //2.2声明一个类的数组不会进行对象的初始化 如果加载的话 只会加载一次 如果不注释掉1.1和1.2和2.1就看不到效果
        Son[] sons = new Son[5];
        System.out.println("=============================");
        //2.3调用子类的常量final也不会进行初始化 如果加载的话 只会加载一次 如果不注释掉1.1和1.2和2.1 2.2就看不到效果
        System.out.println(Son.M);

    }
}
class Father{
    static int b=2;
    static {
        System.out.println("父类被加载");
    }
}
class Son extends Father{
    static {
        System.out.println("子类被加载");
    }
    static int m=100;
    // final表示常量
    static final int M = 1;
}