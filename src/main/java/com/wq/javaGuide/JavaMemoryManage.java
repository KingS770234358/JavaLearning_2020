package com.wq.javaGuide;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JavaMemoryManage {
    @Test
    public void StringTest(){
        String s1 = new String("fasfsaf");// 堆内存的地址值
        String s2 = "fasfsaf";
        System.out.println(s1.intern() == s1);// 输出false,因为一个是堆内存，一个是常量池的内存，故两者是不同的。
        System.out.println(s1.equals(s2));// 输出true
        ReentrantLock lock = new ReentrantLock();
    }
    @Test
    public void StringTest2() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition c = lock.newCondition();
        lock.lockInterruptibly();
        lock.lock();

    }
}
