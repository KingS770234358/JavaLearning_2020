package com.wq.javashizhan;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class p5c15 {

    public static void work1(){
        System.out.println("hello from work1");
    }
    public static void work2(){
        System.out.println("hello from work2");
    }
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        work1();
        ses.schedule(p5c15::work2, 10, TimeUnit.SECONDS);
        ses.shutdown();

    }
}
