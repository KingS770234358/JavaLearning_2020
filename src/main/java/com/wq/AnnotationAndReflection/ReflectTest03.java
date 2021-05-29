package com.wq.AnnotationAndReflection;

public class ReflectTest03 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1.获取系统类的加载器 笔记的 3)
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        // 2.获取系统类加载器的父类加载器--扩展类加载器 笔记的 2)
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);
        // 3.获取扩展类加载器的父类--根加载器(引导类加载器--c/c++) 笔记的 1)
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader); // 根加载器无法直接获取 为null

        // 4.查看当前类是由哪个类加载器加载的
        ClassLoader currentClassLoader = Class.forName("com.wq.AnnotationAndReflection.ReflectTest03").getClassLoader();
        System.out.println(currentClassLoader); // 是系统类加载器加载的
        // 5.查看JDK内置的类是由哪个类加载器加载的
        System.out.println(Object.class.getClassLoader()); // 是根加载器加载的

        // 6.系统类加载器都去哪些路径加载类
        System.out.println(System.getProperty("java.class.path"));
        // D:\IDEA\workspace\JavaLearning\target\classes
        // 因为是maven工程把所有需要加载的类都放入了target/classes中
    }
}
