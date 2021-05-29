package com.wq.mianshiceshi;
/*
*  线程
* */

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class s01container {
    private static Lock lock = new ReentrantLock();
    private static Integer count = 0;
    public static class MyThread implements Runnable{
        @Override
        public void run(){
            //ThreadLocal<Integer> localInt = new ThreadLocal<>();
            //localInt.set(5);

            long tid = Thread.currentThread().getId();
            int min = 5;
            //synchronized (count){
                lock.lock();
                try{
                    while(min!=0) {

                        count += 1;
                        //localInt.set(localInt.get()-1);
                        min-=1;
                        //if(count>25)break;
                        System.out.println("接口方式:" + count +"线程号:" + tid);
                    }
                }

                //Thread.yield();
            //}
            finally {
                    lock.unlock();
                }
        }
    }
    public static class MyCallable implements Callable{
        @Override
        public Integer call() throws Exception {
            while(true){
                count ++;
                System.out.println("mc 输出:"+count);
                if(count>5)break;
                Thread.sleep(1000);
            }
            return count;
        }
    }
    public static class MyExThread extends Thread{
        public void run(){
            while(!interrupted()){
                // System.out.println(interrupted());
                //count++;
                //if(count>5)break;
                //System.out.println("继承方式线程:" + count);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread run");

        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyThread mt1 = new MyThread();
        MyThread mt2 = new MyThread();
        //Thread t1 = new Thread(mt);
        //t1.start();

        Thread t1 = new Thread(mt1, "mt1");
        Thread t2 = new Thread(mt2, "mt2");
        t1.start();
        t2.start();


        //MyCallabe mc = new MyCallable();
        // 用futureTask封装 <>放入返回值类型
        //FutureTask<Integer> ft = new FutureTask<>(mc);
        //Thread t1 = new Thread(ft);
        // System.out.println("mc 返回值:"+ ft.get());

       // MyExThread met = new MyExThread();
        //met.start();
        //Thread.sleep(3000);
        //met.interrupt(); //  interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true


        // ExecutorService executorService = Executors.newCachedThreadPool();
        // ExecutorService executorService = Executors.newFixedThreadPool(5);
        // ExecutorService executorService = Executors.newScheduledThreadPool(5);
        /*
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {// 创建三个线程
            executorService.execute(new MyThread()); // 这里MyThread是Runnable接口的实现类 不是Thread子类
        }
        executorService.shutdown();
        */

    }

}
