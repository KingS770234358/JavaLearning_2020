package com.wq.AnnotationAndReflection;

import java.lang.annotation.*;

// @MyAnnotation
public class AnnotationTest {

    @MyAnnotation(pwd="123",hobbies={"篮球","足球"})
    public void targetTest(){
    }
    @MyAnnotation2("哈哈")
    public void singleParam(){
    }
}

// 定义一个注解
// 一个类java文件中能一定一个类 要定义另外一个类不能加public等
// 1.注解可以加在什么地方
@Target(value={ElementType.METHOD, ElementType.TYPE})
// 2.表示注解在什么地方有效 RUNTIME > CLASS > SOURCE
@Retention(value = RetentionPolicy.RUNTIME)
@Documented // 3.表示注解会生成在javadoc中
@Inherited  // 4.子类可以继承父类的注解
@interface MyAnnotation{

    // 注解包含的配置参数: 参数类型 参数名();
    int id() default 0;
    String name() default "";
    String pwd();
    int age() default -1; // 默认值-1表示不存在
    String[] hobbies();
    String[] courses() default {"高数","线代"};
}

// 单个参数的注解使用value命名参数 使用的时候不需要指定参数
@Target(value={ElementType.METHOD, ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    String value();
}