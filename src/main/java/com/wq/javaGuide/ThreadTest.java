package com.wq.javaGuide;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadTest {

    @Test
    public void allThreads(String[] args){
        // 线程管理对象
        ThreadMXBean hmxb = ManagementFactory.getThreadMXBean();
        // 不获取同步的synchronizer和monitor信息 只获取 线程 和 线程堆栈信息
        ThreadInfo [] tis = hmxb.dumpAllThreads(false, false);
        for( ThreadInfo ti : tis){
            System.out.println(ti.getThreadId()+" "+ti.getThreadName());
        }
    }
}
