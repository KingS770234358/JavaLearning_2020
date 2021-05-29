package com.wq.AOPtest;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntroductionAdviceTest {
    public static void main(String[] args) {

        ITester tester_delegate = new Tester();
        DelegatingIntroductionInterceptor dii = new DelegatingIntroductionInterceptor(tester_delegate);
        ITester tester_delegate2 = new Tester();
        DelegatingIntroductionInterceptor dii2 = new DelegatingIntroductionInterceptor(tester_delegate2);
        /*
            Introduction增强使得 目标类 Developer 新增了 Tester中定义的方法
            目标类可以像调用自己原有的方法一样调用 新增的方法
            Introduction增强更像是多个方法的聚集
        */
        ProxyFactory pf = new ProxyFactory(new Developer());
        /*
        * 基于接口的代理形式
        * 这里必须是：pf.setInterfaces(new Class[]{ITester.class, IDeveloper.class});
        * */
        pf.setInterfaces(new Class[]{ITester.class, IDeveloper.class});
        /*
        * 这里如果使用 和上述一样的 都是包含tester_delegate的dii 则使用Tester是共享的
        * */
        pf.addAdvice(dii2);

        Object proxy = pf.getProxy();
        // 接口代理 无法按对象类型进行转换 只能按接口类型进行转换
        ((IDeveloper)proxy).developSoftware(); // I am working!
        ((ITester)proxy).testSoftware(); // Testing Software!
        // ((Developer)proxy).developSoftware(); // 报错 java.lang.ClassCastException
        // ((Tester)proxy).testSoftware(); // 报错 java.lang.ClassCastException
        /*
        * 基于类的代理形式
        * pf2.setProxyTargetClass(true);
        * pf2.setInterfaces(new Class[]{ITester.class});
        * */
        ProxyFactory pf2 = new ProxyFactory(new Developer());
        pf2.setProxyTargetClass(true);
        pf2.setInterfaces(new Class[]{ITester.class});
        pf2.addAdvice(dii);
        Object proxy2 = pf2.getProxy();
        ((IDeveloper)proxy2).developSoftware(); // I am working!
        ((ITester)proxy2).testSoftware(); // Testing Software!
        ((Developer)proxy2).developSoftware(); // I am working!
        // 基于类的代理形式 使用CGLib动态的生成 目标类Developer的子类 作为 代理类
        // 即proxy是Developer的子类 同时 实现了 IDeveloper，所以可以转换成Developer或者IDeveloper
        // 但无法如下转换成Tester
        // ((Tester)proxy2).testSoftware(); // 报错 java.lang.ClassCastException

        ApplicationContext ac = new ClassPathXmlApplicationContext("application-context.xml");
        Object proxy3 = ac.getBean("proxyFactoryBean");
        Object proxy4 = ac.getBean("proxyFactoryBean");
        ((IDeveloper)proxy3).developSoftware(); // I am working!
        ((ITester)proxy3).testSoftware(); // Testing Software!
        ((IDeveloper)proxy3).developSoftware(); // I am working!
        ((ITester)proxy3).testSoftware(); // Testing Software!
        ((IDeveloper)proxy4).developSoftware(); // I am working!
        ((ITester)proxy4).testSoftware(); // Testing Software!
    }
}
