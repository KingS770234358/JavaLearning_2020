package com.wq.javaGuide;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    private static final int CORE_POOL_SIZE = 5; // 核⼼线程数线程数定义了最⼩可以同时运⾏的线程数量
    private static final int MAX_POOL_SIZE = 10; // 当队列中存放的任务达到队列容量的时候，当前可以同时运⾏的线程数 量变为最⼤线程数。
    private static final int QUEUE_CAPACITY = 100;// 当队列中存放的任务达到队列容量的时候，当前可以同时运⾏的线程数 量变为最⼤线程数。
    private static final Long KEEP_ALIVE_TIME = 1L; // 核⼼线程外的线程不会⽴即销毁，⽽是会等待，直到等待的时间超过了 keepAliveTime才会被回收销毁

    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                CORE_POOL_SIZE, // 核心线程数
                MAX_POOL_SIZE,  // 最大线程数
                KEEP_ALIVE_TIME,// 等待线程 存活时间
                TimeUnit.SECONDS,//存活时间单位
                new ArrayBlockingQueue<>(QUEUE_CAPACITY), //用于存法任务的队列
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );
        for(int i =0;i<10;i++){
            Runnable worker = new MyRunnable("" + i);
            tpe.execute(worker);
        }
        //终⽌线程池 executor.shutdown();
        while (!tpe.isTerminated()) { }
        System.out.println("Finished all threads");
    }
}
