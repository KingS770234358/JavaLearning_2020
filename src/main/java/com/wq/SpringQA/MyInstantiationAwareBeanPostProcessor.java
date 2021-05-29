package com.wq.SpringQA;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/*
* InstantiationAwareBeanPostProcessor 接口本质是BeanPostProcessor的子接口，
* 一般我们继承Spring为其提供的适配器类InstantiationAwareBeanPostProcessorAdapter来使用它
* */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    /* 构造函数 */
    public MyInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("4. InstantiationAwareBeanPostProcessorAdapter实现类构造器！！");
    }

    // 接口方法、实例化Bean之前调用
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println("5. InstantiationAwareBeanPostProcessorAdapter调用postProcessBeforeInstantiation方法");
        System.out.println("beanName: " + beanName);

        return null;
    }

    // 接口方法、实例化Bean之后调用
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("7. InstantiationAwareBeanPostProcessorAdapter调用postProcessAfterInstantiation方法");
        return true;
    }

    // 接口方法、设置某个属性时调用 postProcessPropertyValues用来操作属性，返回值也应该是PropertyValues对象

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("8. InstantiationAwareBeanPostProcessorAdapter调用postProcessPropertyValues方法");
        System.out.println("beanName: " + beanName);
        System.out.println("pvs: " + pvs);
        for (PropertyDescriptor pd : pds){
            System.out.print(pd + " ");
        }
        return pvs;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("14. InstantiationAwareBeanPostProcessorAdapter调用postProcessBeforeInitialization方法");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("18. InstantiationAwareBeanPostProcessorAdapter调用postProcessAfterInitialization方法");
        System.out.println("beanName: " + beanName);
        return bean;
    }
}
