package com.wq.javashizhan.parallelDemo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10;
    private ForkJoinSumCalculator(long[] numbers, int start, int end){// 私有构造函数用于 compute 函数创建子任务
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    public ForkJoinSumCalculator(long[] numbers){ // 共有函数供外部调用
        // this.numbers = numbers;
        this(numbers, 0, numbers.length); // 创建主任务
    }

    @Override
    public Long compute(){
        int length = end - start;
        if(length<=THRESHOLD){
            return computeSequentialy();
        }
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start+length/2); // 最终子任务是 左闭右开的
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start+length/2, end);  // 最终子任务是 左闭右开的
        leftTask.fork();
        // rightTask.fork();
        Long rightRes = rightTask.compute(); // 右边结果直接复用当前线程 减小创建线程的开销
        Long leftRes = leftTask.join();
        //return leftTask.join() + rightTask.join();
        return leftRes + rightRes;
    }
    private long computeSequentialy(){
        long sum=0;
        for(int i=start; i<end;i++){
            sum+=numbers[i];
        }
        return sum;
    }
    private static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();// 这里需要把 LongStream中的元素都放进long[]中 影响性能
        // ForkJoinTask是RecursiveTask的父类
        ForkJoinTask<Long> fjt = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(fjt); // 调用ForkJoinSumCalculator对象的compute方法
    }

    public static void main(String[] args) {
        long res = forkJoinSum(100);
        System.out.println(res);
    }
}
