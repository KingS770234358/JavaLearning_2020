package com.wq.javashizhan.chapter17;

import java.util.concurrent.Flow;

public class ProcessorDemo {
    // 真正的发布者对象
    public static Flow.Publisher<TemperatureProducer> getTemperatures(String town){
        // 创建 订阅者 和 发布者 的subscription对象
        return subscriber -> {
            TemperatureProcessor tpor = new TemperatureProcessor();
            tpor.subscribe(subscriber);
            tpor.onSubscribe(new TemperatureSubscription(tpor, town));
        };
    }

    public static void main(String[] args) {
        getTemperatures("New York")
                .subscribe(new TemperatureSubscriber());
    }
}
