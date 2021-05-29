package com.wq.AnnotationAndReflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

/**
 * 练习反射操作注解
 * ORM
 */
public class ReflectTest08 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class stuClass = Class.forName("com.wq.AnnotationAndReflection.student");

        // 通过反射获得注解
        Annotation[] annotations = stuClass.getAnnotations();
        for(Annotation a: annotations){
            System.out.println(a);
        }

        // 获得某个具体类的Annotation的value
        TableName tn = (TableName) stuClass.getAnnotation(TableName.class);
        System.out.println(tn.value());

        // 获得具体属性的注解的value
        Field field = stuClass.getDeclaredField("name");
        FieldName fn = field.getAnnotation(FieldName.class);
        System.out.println(fn.columnName());
        System.out.println(fn.type());
        System.out.println(fn.length());
    }
}
// 创建练习用的类
@TableName("db_student")
class student{
    @FieldName(columnName = "db_id", type = "int", length = 2)
    private int id;
    @FieldName(columnName = "db_name", type = "varchar", length = 3)
    private String name;
    @FieldName(columnName = "db_age", type = "int", length = 4)
    private int age;

    public student() {
    }

    public student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

// 创建练习用的类的注解
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface TableName{
    String value();
}
// 属性的注解
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface FieldName{
    String columnName();
    String type();
    int length();
}

