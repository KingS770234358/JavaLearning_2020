package com.wq.SpringQA;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor(){
        super();
        System.out.println("3. BeanPostProcessor实现类构造器");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("13. 调用BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！");
        System.out.println("bean:" + bean + " beanName:" + beanName);
        return bean;  // 把经过更改处理后的对象返回
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("17. 调用BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！");
        System.out.println("bean:" + bean + " beanName:" + beanName);
        return bean;  // 把经过更改处理后的对象返回
    }
}
