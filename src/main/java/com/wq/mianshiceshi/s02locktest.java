package com.wq.mianshiceshi;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class s02locktest {
    private static Integer share = 0;
    public static class A extends Thread{
        @Override
        public void run(){
            System.out.println("this s A thread");
        }
        public synchronized void preRun(){
            try{
                wait(); // 这是先运行的进行 调用wait()方法会被挂起
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是preRun 刚刚被挂起了");
        }
        public synchronized void laterRun(){
            try{
                System.out.println("我先用下 待会儿唤醒所有其他的");
                notifyAll();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void preRunWait(){
            lock.lock();
            try{
                condition.await();// 让出锁
                System.out.println("我刚刚让出锁，被挂起");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void laterRunSignal(){
            lock.lock();
            try{
                System.out.println("前面的进程让出来了，我先执行");
                condition.signalAll();// 唤醒所有等待的线程
            }finally {
                System.out.println("我准备让出锁让前面的进程继续执行了");
                lock.unlock();
            }
        }
    }
    public static class B extends Thread{
        private A a;
        B(A a){
            this.a = a;
        }
        @Override
        public void run(){
            try {
                a.join();    // B线程运行的时候 需要A线程的协助（需要A线程执行完毕之后才能继续执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is B thread");
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 线程之间的协作
        A a = new A();
        B b = new B(a);
        b.start();
        a.start();
        System.out.println("===================================");
        // 在synchronized锁住的方法中 用 wait 让出使用权挂起
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(()->a.preRun());
        es.execute(()->a.laterRun());
        // es.shutdown(); // 用完线程池 记得关闭
        System.out.println("===================================");

        // 在lock锁住的代码块中 Condition 上调用await()方法线程等待 调用signal 和 signalAll方法唤醒等待线程
        es.execute(()->a.preRunWait());
        es.execute(()->a.laterRunSignal());
        System.out.println("===================================");

        final int countDownLatchTestThreadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(countDownLatchTestThreadNum);
        for (int i = 0; i < countDownLatchTestThreadNum; i++) {
            es.execute(() -> {
                System.out.print("run..");
                countDownLatch.countDown(); // 所有线程countDownLatch.countDown()使之0，就一起去唤醒countDownLatch.await()的线程
            });
        }
        // 上面启动10个线程后 不执行这里的await方法的话 主线程就直接结束 等待上面线程结束
        // 但是 这里调用了await方法之后 主线程就会等子线程把countDownLatch置为0之后再继续运行
        countDownLatch.await(); // 这里要抛出或者捕获异常
        System.out.println("end");
        System.out.println("===================================");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(countDownLatchTestThreadNum);
        for (int i = 0; i < countDownLatchTestThreadNum; i++) {
            es.execute(() -> {
                System.out.print("before.. ");
                try{
                    cyclicBarrier.await();// 每个进程都进入await状态，达到数量后 每个线程在继续执行接下来的部分
                }catch (InterruptedException | BrokenBarrierException  e){
                }
                System.out.print("after.. ");
            });
        }
        System.out.println("===================================");
        final int clientRequestNumPerTime = 3;
        Semaphore s = new Semaphore(clientRequestNumPerTime);
        for (int i = 0;i<countDownLatchTestThreadNum*2;i++){
            es.execute(()->{
                try{
                    s.acquire();// 获得一个信号量（锁）
                    System.out.println(s.availablePermits() + " ");
                    // availablePermits（）返回此Semaphore对象中当前可用的许可数，许可的数量有可能实时在改变，并不是固定的数量。
                    // drainPermits（）可获取并返回立即可用的所有许可个数，并且将可用许可置0。
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    s.release();// 释放信号量
                }
            });
        }
        es.shutdown(); // 用完线程池 记得关闭


        System.out.println();
        System.out.println("===================================");
        FutureTask<Integer> ftt  = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                //Thread.sleep(10);
                return 20000;
            }
        });
        Thread tftt = new Thread(ftt);
        tftt.start();
        Thread otherThread = new Thread(() -> {
            System.out.println("other task is running...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        otherThread.start();
        System.out.println(ftt.get());



    }
}
