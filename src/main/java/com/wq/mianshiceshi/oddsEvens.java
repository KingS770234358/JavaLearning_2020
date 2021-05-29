package com.wq.mianshiceshi;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class oddsEvens {
    public static int i=0;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static class oddsThread extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    oddsEvens.lock.lock();
                    if(oddsEvens.i%2==0){
                        oddsEvens.condition.await();
                    }
                    System.out.println(Thread.currentThread() + " " + i);
                    i++;
                    Thread.sleep(1);
                    oddsEvens.condition.signal();
                }catch(Exception e){

                }finally{
                    oddsEvens.lock.unlock();
                }
            }
        }
    }
    public static class evensThread extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    oddsEvens.lock.lock();
                    if(oddsEvens.i%2!=0){
                        oddsEvens.condition.await();
                    }
                    System.out.println(Thread.currentThread() + " " + i);
                    i++;
                    Thread.sleep(1000);
                    oddsEvens.condition.signal();
                }catch(Exception e){

                }finally{
                    oddsEvens.lock.unlock();
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread odds = new oddsThread();
        Thread evens = new evensThread();
        odds.start();
        evens.start();
    }
}
