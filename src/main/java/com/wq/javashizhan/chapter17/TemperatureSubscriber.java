package com.wq.javashizhan.chapter17;

import java.util.concurrent.Flow;

public class TemperatureSubscriber implements Flow.Subscriber<TemperatureProducer> {
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription){
        this.subscription = subscription;
        this.subscription.request(1);
    }
    @Override
    public void onNext(TemperatureProducer temperatureProducer){
        System.out.println(temperatureProducer);
        subscription.request(3); // 这里执行一次request执行 3次 onNext操作
    }
    @Override
    public void onError(Throwable t){
        System.err.println(t.getMessage());
        // 这里在 onError 方法中补充 打印完错误信息的同时 再次调用request方法 放入线程池运行
        // subscription.request(1); //
    }
    @Override
    public void onComplete(){
        System.out.println("Done!");
    }
}
