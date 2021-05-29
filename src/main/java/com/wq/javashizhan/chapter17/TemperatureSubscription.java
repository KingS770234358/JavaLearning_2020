package com.wq.javashizhan.chapter17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

public class TemperatureSubscription implements Flow.Subscription {
    private final Flow.Subscriber<? super TemperatureProducer> subscriber;
    // private static final ExecutorService es = Executors.newSingleThreadExecutor(); // 单例线程池
    private static final ExecutorService es = Executors.newFixedThreadPool(2); // 单例线程池
    private final String town;
    public TemperatureSubscription(Flow.Subscriber<? super TemperatureProducer> subscriber, String town){
        this.subscriber = subscriber;
        this.town = town;
    }
    @Override
    /*
    // request->onNext->request->onNext... 使用栈递归调用的方法 如果没有onError方法停止request 则会栈溢出
    public void request(long n){ // 限制接受的数量
        for(long i=0L; i<n; i++){
            try{
                subscriber.onNext(TemperatureProducer.fetch(town));
            }catch (Exception e){
                subscriber.onError(e);
                break;
            }
        }
    }
    */
    // 使用线程池管理的线程处理request请求
    public void request(long n){ // 限制接受的数量
        es.submit(()->{
            //System.out.println("当前调用request方法的线程:" + Thread.currentThread().getId());
            for(long i=0L; i<n; i++){
                //System.out.println("==="+i+"===");
                try{
                    // 订阅者 一直在这里监听 发布者是否发布消息(这里的实现是发布者一定发布消息 但随概率抛出异常)
                    subscriber.onNext(TemperatureProducer.fetch(town));
                }catch (Exception e){
                    subscriber.onError(e);
                    break;
                }
            }
        });
    }
    @Override
    public void cancel(){
        subscriber.onComplete();
    }
}
