package com.wq.SpringQA;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor(){
        super();
        System.out.println("1. BeanFactoryPostProcessor实现类构造器！！");
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("2. BeanFactoryPostProcessor调用postProcessBeanFactory方法");
        BeanDefinition bd = beanFactory.getBeanDefinition("person111");
        bd.getPropertyValues().addPropertyValue("phone", "110");
        bd.getPropertyValues().addPropertyValue("name", "李四工厂");
        bd.getPropertyValues().addPropertyValue("address", "广州工厂");
    }
}
