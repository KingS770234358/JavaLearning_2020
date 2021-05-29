package com.wq.javashizhan.chapter17;

import java.util.concurrent.Flow;

public class TemperatureProcessor  implements Flow.Processor<TemperatureProducer,TemperatureProducer> {
    private Flow.Subscriber<? super TemperatureProducer> subscriber;
    @Override
    public void subscribe(Flow.Subscriber<? super TemperatureProducer> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(TemperatureProducer temperatureProducer) {
        this.subscriber.onNext(
                new TemperatureProducer(temperatureProducer.getTown()+" what a town",
                        (temperatureProducer.getTemp() - 32)*5/9));
    }

    @Override
    public void onError(Throwable throwable) {
        this.subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        this.subscriber.onComplete();
    }
}
