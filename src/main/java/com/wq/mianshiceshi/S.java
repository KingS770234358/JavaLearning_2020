package com.wq.mianshiceshi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;

public class S {
    public static synchronized void func1(){
        for(int i=0;i<10;i++){
            System.out.print(i+" ");
        }
    }
    public static int func2(){
        int a = 1;
        int b = 2;
        return a + b;
    }
    public static void main(String[] args) {

        int c = func2();
        System.out.println(c);
    }

}
