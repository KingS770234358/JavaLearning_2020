package com.wq.javashizhan.chapter17;

import java.util.concurrent.Flow;

/*
* 反应式宣言：
* 1）响应性：快
* 2）韧性：失败依然能继续服务
* 3）弹性：根据工作负载动态调整
* 4）消息驱动：组件之间以消息的形式传递信息
* 事件/发布  消息/订阅  信号/监听  事件循环/背压
* java.util.concurrent.Flow: 发布者 订阅者 订阅 处理者
*
* 不断的汇报温度
* */
public class ReactiveProgramming {
    // 真正的发布者对象
    public static Flow.Publisher<TemperatureProducer> getTemperatures(String town){
        // 创建 订阅者 和 发布者 的subscription对象
        return subscriber -> subscriber.onSubscribe(new TemperatureSubscription(subscriber, town));
    }


    public static void main(String[] args) {
        getTemperatures("New York") // 返回一个New York温度的发布者
                .subscribe(new TemperatureSubscriber()); // 创建一个新的订阅者
        /*
            1. getTemperatures("New York") 得到一个纽约天气的发布者函数
            2. new TemperatureSubscriber() 新建一个订阅者
            3. subscriber -> subscriber.onSubscribe(new TemperatureSubscription(subscriber, town));
                ① 首先先 new TemperatureSubscription(subscriber, town) 根据 订阅者 和 发布者 建立一个subscription对象
                ② subscriber.onSubscribe(new TemperatureSubscription(subscriber, town))
                  调用 subscription对象的 request 方法                          <----
            4. request中触发subscriber的onNext方法 onNext方法中调用request方法   <----

            整个过程中，Flow.Publisher<TemperatureProducer> 是 TemperatureProducer对象的发布者(某个town气温的发布者)
                       Flow.Subscriber<TemperatureProducer>是 TemperatureProducer对象的订阅者
                       TemperatureSubscription 负责绑定 发布者(town) 和 订阅者(subscriber)
            subscriber -> subscriber.onSubscribe(只执行一次) ===> 【这里是源头 发布者 订阅者 订阅
            三个对象都创建好了之后 是订阅者主动使用了onSubscribe方法 开始了 request->oinNext的循环调用】
            发布者 这里 执行 订阅者 Flow.Subscriber<TemperatureProducer> TemperatureSubscriber的
            onSubscribe方法(只一次), 订阅者 接受 TemperatureSubscription对象， 并执行TemperatureSubscription对象的
            request(long n)方法 , request方法调用 订阅者 的onNext方法 n次(打印出给定城市的气温), 在 订阅者的 onNext方法中
            还要 继续执行 TemperatureSubscription对象的 request(long n)方法, 如此反复, request()方法在执行onNext方法的
            时候 出现异常 break(发布者内 发布城市温度的时候 根据随机数 随机地抛出异常 模拟现实的异常)

            1）去除上面的随机异常 则request->onNext->request->onNext....反复 会导致【栈溢出】
            解决方法: subscription 中开一个线程池(这里开的是单例线程池)
            request 中 用线程池里的线程 执行onNext任务, 这样request和onNext的交替
            执行就不会一直使用栈 而是都使用线程池里的线程

            2）这里因为运行的是单例线程池，所以当一个线程抛出异常 subscribe执行了onError方法的时候，
            就不会在调用request方法了，线程池没关闭的话就会一直等待
            上面onNext里面是有继续调用request方法
            这里在 onError 方法中补充 打印完错误信息的同时 再次调用request方法 放入线程池运行\

            3）试着使用 fixedThreadPool 发现
            其实 在当前线程中 因为request中传入的n=1 也就是for循环体 只执行了一次，也就是 onNext方法只执行一次
            noNext方法中 执行request开启一个新的线程之后 当前线程就结束了
            所以 【线程池里从头到尾其实都只有一个线程是运行的】 因此当抛出异常的时候 onError中不调用request方法
            就不会再启动新的线程 因此 线程池闲置

            4）n=3
            这种情况下 一次request执行三次 onNext 下次就会调用三次request启动三个线程(onNext调用了request函数)
            这样 要等所有的thread都抛出异常才能完全停止 否则比线程池大小多出来的线程会阻塞，
            试着 调大抛出异常的概率 验证下 是否会完全停止
            结论是会停止
            要注意 在一个request启动的线程中 异常捕获后 catch 执行onError方法就会 break了 所以剩下的线程都不会启动了
            因此一次request方法 不一定能顺利把3个onNext都执行完 也就是不一定能顺利执行request三次(再启三个线程)

         */

    }
}
