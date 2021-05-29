package com.wq.AOPtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectIntroduction {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application-context.xml");
        Object o1 = ac.getBean("developer");
        Object o2 = ac.getBean("developer");
        ((Developer)o1).developSoftware();
        /*
        * 配置中指定 proxy-target-class="true" 使用 基于类的代理方式
        * 因此使用CGLib的方式 生成目标对象的子类 作为代理对象
        * 该代理对象继承于目标对象，因此可以转换成目标对象
        * 但是该代理对象无法转换成新增接口的实例，只能转换成新增接口类型
        * */
        ((ITester)o1).testSoftware();
        ((Developer)o1).developSoftware();
        ((ITester)o1).testSoftware();
        ((Developer)o2).developSoftware();
        ((ITester)o2).testSoftware();
    }
}
