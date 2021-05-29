package com.wq.AnnotationAndReflection;

import java.lang.annotation.ElementType;

/**
 * 测试反射机制
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1.通过反射获取类的class对象
        // 一个类在加载后 类的整个结构都会被封装在Class对象里
        // <?>通过泛型接收 是不确定的
        // 这里需要抛出找不到类的异常
        Class<?> c1 = Class.forName("com.wq.AnnotationAndReflection.User");
        // 获取Class对象的方法2:也可以继承Object对象, 使用Object对象的getClass方法
        System.out.println(c1);
        Class<?> c2 = Class.forName("com.wq.AnnotationAndReflection.User");
        // 一个类在内存中只有一个Class对象
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        //==============================================================================
        // 2.获得Class类的几种方式
        User user = new VIP1();
        System.out.println(user.getName());
        // 2.1 通过对象获得
        Class userClass1 = user.getClass();
        // 2.2 forName
        Class userClass2 = Class.forName("com.wq.AnnotationAndReflection.User");
        // 2.3 通过 类名.class获得
        Class userClass3 = User.class;
        // 2.4 基本内置类型的包装类的TYPE属性
        System.out.println(userClass1.hashCode()+" "
                +userClass2.hashCode()+" "
                +userClass3.hashCode()+" "
                +Integer.TYPE+" "+Integer.TYPE.hashCode());
        // 2.5 获得父类 user使用的是VIP1的构造函数
        Class VIPParentClass = userClass1.getSuperclass();
        System.out.println(VIPParentClass);
        //==============================================================================
        // 3.所有类型的Class对象
        Class classClass = Object.class;         // 任意一个类的class对象
        Class interfaceClass = Comparable.class; // 任意一个接口的class对象
        Class arrayClass = String[].class;       // 任意一个数组的class对象
        Class array2DClass = String[][].class;   // 任意一个2D数组的class对象
        Class annotationClass = Override.class;  // 任意一个注解的class对象
        Class enumClass = ElementType.class;     // 任意一个枚举的class对象
        Class primitiveClass = Integer.class;    // 任意一个基本类型的class对象
        Class voidClass = void.class;            // 空对象的class对象
        Class classObjectClass = Class.class;    // Class类本身的class对象
        System.out.println(classClass);
        System.out.println(interfaceClass);
        System.out.println(arrayClass);
        System.out.println(array2DClass);
        System.out.println(annotationClass);
        System.out.println(enumClass);
        System.out.println(primitiveClass);
        System.out.println(voidClass);
        System.out.println(classObjectClass);
        // 两个长度不一样的数组 他们的Class对象也一样
        // 只要类型和维度一样 他们的Class对象就是一样的
        int [] a = new int[10];
        int [] b = new int[5];
        System.out.println(a.getClass().hashCode());
        System.out.println(b.getClass().hashCode());
        //==============================================================================
        // 4.类的加载
        /**
         * 1.加载.class文件到内存方法区, 在堆里会产生一个这个类对应的Class对象
         * 2.链接 栈中 static变量分配初始值 m=0
         * 3.初始化 合并静态块和静态方法
         *     static{
         *         System.out.println("A类静态代码块初始化");
         *         m = 300;
         *     }
         *     static int m = 100;
         *     <clinit>{
         *         System.out.println("A类静态代码块初始化");
         *         m = 300;
         *         m = 100;
         *     }
         *     m = 100;
         *     调换两者顺序 则结果m的值发生改变
         */
        A aClass = new A();
        System.out.println(aClass.m);
    }
}
// 用于测试类的加载
class A{

    static{
        System.out.println("A类静态代码块初始化");
        m = 300;
    }
    static int m = 100;
    public A(){
        System.out.println("A类的无参构造器初始化");
    }
}
// 实体类
class User{
    private String name;
    private int id;
    private int age;
    User(){
        this.setName("user");
    }

    public User(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }
}
class VIP1 extends User{
    public VIP1(){
        this.setName("vip1");
    }
}
