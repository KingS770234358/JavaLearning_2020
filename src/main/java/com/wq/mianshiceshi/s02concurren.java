package com.wq.mianshiceshi;
import java.util.concurrent.*; //（J.U.C）
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class s02concurren {
    public static class LockExample{
        private Lock lock = new ReentrantLock();
        public void func(){
            lock.lock();
            try {
                for(int i=0;i<10;i++){
                    System.out.print(i+" ");
                }
            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        LockExample le = new LockExample();
        for(int i=0;i<2;i++){
            es.execute(()->le.func());
            //es.execute(()->le.func());
        }
        es.shutdown();
    }
}
