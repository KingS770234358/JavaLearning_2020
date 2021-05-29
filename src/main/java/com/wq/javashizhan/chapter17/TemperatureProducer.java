package com.wq.javashizhan.chapter17;

import java.util.Random;

public class TemperatureProducer {
    private static final Random random = new Random(); // 生成随机温度使用
    private final String town;
    private final int temp;
    public TemperatureProducer(String town, int temp){
        this.temp = temp;
        this.town = town;
    }
    public static TemperatureProducer fetch(String town){
        // 根据随机数 抛出异常 模拟异常抛出
        //if(random.nextInt(10)==5) throw new RuntimeException("Error!");
        // 调大抛出异常的概率 看 onError不调用request的情况下 线程池最后是不是会空闲
        if(random.nextInt(10)>4) throw new RuntimeException("Error!");
        return new TemperatureProducer(town, random.nextInt(100));
    }
    @Override
    public String toString() {
        return "TemperatureProducer{" +
                "town='" + town + '\'' +
                ", temp=" + temp +
                '}';
    }
    public int getTemp(){
        return this.temp;
    }
    public String getTown(){
        return this.town;
    }

}
