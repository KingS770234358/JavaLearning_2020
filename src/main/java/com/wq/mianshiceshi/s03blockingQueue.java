package com.wq.mianshiceshi;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class s03blockingQueue {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
    private static Lock lock = new ReentrantLock();

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                queue.put("product");
                System.out.print("produce..");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            lock.lock();
//
//            lock.unlock();
        }
    }

    private static class Consumer extends Thread {

        @Override
        public void run() {
            try {
                String product = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.print("consume..");
            lock.unlock();
        }
    }

    public static class RecursiveTaskForkJoinExample extends RecursiveTask<Integer> { // 这里Integer是协作的线程返回值类型
        private final int workThreshold = 5;
        private int start;
        private int end;
        public RecursiveTaskForkJoinExample(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected Integer compute(){ // 重写父类的计算方法
            int result = 0;
            if(end-start<workThreshold){
                // 当工作区间足够小的时候直接计算
                for(int i=start; i<=end; i++){
                    result += i;
                }
            }else{
                // 当区间大于阈值的时候 拆分成小任务运行
                int mid = (end + start) / 2;
                RecursiveTaskForkJoinExample rtfje1 = new RecursiveTaskForkJoinExample(start,mid);
                RecursiveTaskForkJoinExample rtfje2 = new RecursiveTaskForkJoinExample(mid+1,end);
                rtfje1.fork();//开始运行子区间任务
                rtfje2.fork();//开始运行子区间任务
                result = rtfje1.join() + rtfje2.join(); // 将子区间运行任务的结果返回 相加
            }
            return result;
        }
    }

    public static class AtomicTest{
        private AtomicInteger ai = new AtomicInteger();
        public void add(){
            ai.getAndIncrement();
        }
        public int getAtomicVal(){
            return ai.get();
        }
    }
    public static class NoAtomicTest{
        private int ai = 0;
        public void add(){
            ai++;
        }
        public int getAtomicVal(){
            return ai;
        }
    }

    public static class VolaTest{
        // private static boolean flag = false;
        private static volatile boolean flag = false;
        public void doWhileVola(){
            while(!flag){
                //System.out.println("flag==0"); // 当循环里有输出的时候 即使没有volatile也会停止
            }
        }
        public void setVola(){
            flag = true;
        }
    }

    public static void main(String[] args) {

        /*
        * 1. blockingqueue 生产者 消费者
        * */
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        for (int i = 0; i < 5; i++) {
            Consumer consumer = new Consumer();
            consumer.start();
        }
        // 没有下面生产的3个物品 上面剩余3个线程就会堵住
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        System.out.println("===================================");

        /*
        *  2. RecursiveTask      fork join
        * 工作窃取算法
        * */
        RecursiveTaskForkJoinExample rtfje = new RecursiveTaskForkJoinExample(0,30);
        /*
           Forkjoin 任务 要使用 ForkjoinPool池 来启动 它是一个特殊的线程池，线程数量取决于 CPU 核数
           实现了工作窃取算法来提高 CPU 的利用率。每个线程都维护了一个双端队列，用来存储需要执行的任务。
           工作窃取算法允许空闲的线程从其它线程的双端队列中窃取一个最晚的任务（避免和队列所属线程发生竞争）
           但是如果队列中只有一个任务时还是会发生竞争。
        * */
        ForkJoinPool fjp = new ForkJoinPool();
        // 把fj任务放入fjp  结果要使用Future接收
        Future result = fjp.submit(rtfje);
        try {
            System.out.println("===="+ result.get() +"====");
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("================================");

        /*
        * 3. 使用AtomicInteger类型使得原来的不安全线程 变得安全
        * */
        final int cdlCount = 1000;
        AtomicTest at = new AtomicTest();
        //NoAtomicTest at = new NoAtomicTest(); // 不适用Atomic的不安全的情况
        final CountDownLatch cdl = new CountDownLatch(cdlCount);
        ExecutorService es  = Executors.newCachedThreadPool();
        for(int i=0;i<cdlCount;i++){
            es.execute(()->{
                at.add();
                cdl.countDown();
            });
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(at.getAtomicVal());
        System.out.println("================================");

        VolaTest vt = new VolaTest();
        es.execute(()->vt.doWhileVola());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vt.setVola(); // 运行setVola方法的线程对volatile变量作出的改动 能被执行doWhileVola的线程马上知道

        /*
        * 有序性：在本线程内观察，所有操作都是有序的。在一个线程观察另一个线程，所有操作都是无序的
        * volatile 关键字通过添加内存屏障的方式来禁止指令重排，即重排序时不能把后面的指令放到内存屏障之前。
        * 可以通过 synchronized 来保证有序性，它保证每个时刻只有一个线程执行同步代码，相当于是让线程顺序执行同步代码。
        * 指令即使重排序也要遵循下述规则
        * 1. 单一线程原则（单一线程内 程序前面的操作先行发生于后面的操作
        *
        * 2. 管程锁定规则（一个锁的unlock操作先于下一个<lock>操作之前
        * 3. <volatile>规则（对一个volatile变量的写操作 先行发生于 【后面】对这个变量的读操作
        *
        * 4. 线程启动原则（thread.start()先行发生于thread的每一个动作
        * 5. 线程加入原则（thread.join() thread所有动作的结束 先行发生于thread的join方法返回
        * 6. 线程中断原则（interrupt() thread的interrupt()方法的调用先行发生于 被中断的线程检测到中断事件的发生 可通过interrupted()方法判断是否有中断发生
        *
        * 7. 对象终结规则（对象的初始化完成-构造函数执行结束 先行于 它的finalize()方法的开始
        *
        * 8. 传递性（如果操作A先行发生于操作B，操作B先行发生于操作C，则操作A先行发生于操作C
        * */

        /*
        *   线程安全的实现方式
        * 不可变 final 关键字修饰的基本数据类型 String 枚举 Number 部分子类，如 Long 和 Double 等数值包装类型，BigInteger 和 BigDecimal 等大数据类型
        * AtomicInteger 和 AtomicLong 则是可变的。
        * 对于集合类型，可以使用 Collections.unmodifiableXXX() 方法来获取一个不可变的集合。
        * */
        Map<String,Integer> map = new HashMap<String,Integer>();
        Map<String, Integer> unmodifiableMap = Collections.unmodifiableMap(map);
        // unmodifiableMap.put("a", 1); // UnsupportedOperationException 对集合进行修改的方法都直接抛出异常


        ThreadLocal threadLocal1 = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        Thread thread1 = new Thread(()->{
            threadLocal1.set(1);
            // threadLocal2.set(1);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(threadLocal1.get());
            threadLocal1.remove();
        });
        Thread thread2 = new Thread(()->{
            threadLocal1.set(333);  // thread2线程中 在threadLocal变量中的设置和删除 不影响thread1的值
            threadLocal1.remove();
        });
        /*
        * 每个Thread对象都有一个对应的ThreadLocal.ThreadLocalMap对象
        * 存储键值对 <ThreadLocal, Value> 大概意思是我在ThreadLocal中存储的值是Value
        * ThreadLocal.ThreadLocalMap 可以通过ThreadLocal取得对应的值
        * 就像 每个线程都是一个人， 有一个threadLocal专门存储所有人的名字，有一个threadLocal专门存储人的性别
        * 这样 人要是想知道自己的名字或性别 就要 使用自己的 ThreadLocal.ThreadLocalMap对象 通过threadLocal取得对应的信息
        * threadLocal只会对所在线程的值进行增删改查
        * */
        thread1.start();
        thread2.start();

        /*
        * 1. 给线程起个有意义的名字，方便找 Bug
        * 2. 缩小同步范围，减少锁争用。例如对于 synchronized，应该尽量使用同步块而不是同步方法。
        * 3. 多用同步工具少用 wait() 和 notify()。用 wait() 和 notify() 很难实现复杂控制流；
        * CountDownLatch, CyclicBarrier, Semaphore 和 Exchanger 这些同步类简化了编码操作
        * 4. 使用 BlockingQueue 实现生产者消费者问题。
        * 5. 多用并发集合少用同步集合，例如应该使用 ConcurrentHashMap 而不是 Hashtable。
        * 6. 使用本地变量和不可变类来保证线程安全。
        * 7. 使用线程池而不是直接创建线程
        * */
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService es2 = Executors.newFixedThreadPool(5);
        for(int i=0;i<100;i++){
            es2.execute(()->{
                System.out.println(Thread.currentThread().getId());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 内存不够 回收
        SoftReference<Integer> sri = new SoftReference<Integer>(new Integer(5));
        // 下次垃圾回收 就回收
        WeakReference<Integer> wri = new WeakReference<Integer>(new Integer(5));
        // 只在回收的时候 收到系统通知
        PhantomReference<Object> pri = new PhantomReference<Object>(new Object(), null);
    }
}