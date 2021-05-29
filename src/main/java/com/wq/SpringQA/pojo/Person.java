package com.wq.SpringQA.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
* Bean的生命周期测试
* 通过创建自己的Bean实现 生命周期 中的一些接口方法
* 调用Bean自身的方法和Bean级生命周期接口方法
* */
public class Person implements BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean, ApplicationContextAware {
    private String name;
    private String address;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;
    private ApplicationContext applicationContext;

    public Person(){ // 最普通的Bean构造器函数
        System.out.println("6. 执行Bean的构造器函数实例化");
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        System.out.println("9.1 为Bean注入属性name");
        this.name = name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        System.out.println("9.2 为Bean注入属性address");
        this.address = address;
    }
    public int getPhone(){
        return phone;
    }
    public void setPhone(int phone){
        System.out.println("9.3 为Bean注入属性phone");
        this.phone = phone;
    }
    @Override
    public String toString(){
        return "Person [address=" + address + ", name=" + name + ", phone="
                         + phone + "]";
    }

    /* BeanNameAware接口的方法实现 */
    @Override
    public void setBeanName(String arg0) {
        System.out.println("10. 调用BeanNameAware.setBeanName() arg0：" + arg0);
        this.beanName = arg0;
    }

    /* BeanFactoryAware接口的方法实现 */
    public void setBeanFactory(BeanFactory arg0) throws BeansException {
        System.out.println("11. 调用BeanFactoryAware.setBeanFactory() arg0：" + arg0);
        this.beanFactory = arg0;
    }

    /* ApplicationContextAware接口的方法实现 */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("12. 调用ApplicationContextAware.setApplicationContext() applicationContext：" + applicationContext);
        this.applicationContext = applicationContext;
    }
    /* InitializingBean接口的方法实现 */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("15. 调用InitializingBean.afterPropertiesSet()");
    }
    /* 通过<bean>的init-method属性指定的初始化方法 */
    public void myInit() {
        System.out.println("16. 调用<bean>的init-method属性指定的初始化方法");
    }

    /* DiposibleBean接口的方法实现 */
    @Override
    public void destroy() throws Exception {
        System.out.println("20. 调用DiposibleBean.destory()");
    }
    /* 通过<bean>的destroy-method属性指定的初始化方法 */
    public void myDestory() {
        System.out.println("21. 调用<bean>的destroy-method属性指定的初始化方法");
    }

}