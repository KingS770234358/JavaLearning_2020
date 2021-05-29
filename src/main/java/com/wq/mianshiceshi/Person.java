package com.wq.mianshiceshi;

public class Person {

    private static boolean a; // 静态变量
    private boolean b; // 实例变量


    // 静态的  ===  可以直接通过类名调用
    public static void sayHello(){
        System.out.println(a);  // 静态方法中不能使用 实例变量  只能使用静态变量
    }

    // 非静态的  ===  必须实例化 才能使用
    public void sayA(){
        System.out.println(a);
        System.out.println(b);
    }

    public static void main(String[] args) {

        Person.sayHello();

        Person p1 = new Person();
        p1.sayHello();
        p1.sayA();
    }
}



