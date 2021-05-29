package com.wq.javashizhan.chapter16;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class AsynTest {
    private String name;
    public AsynTest(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    Random random = new Random();
    // 模拟延迟1秒
    public static void delay(){
        try{
            Thread.sleep(1000L);
        }catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
    // 计算价格的算法
    private double calculatePrice(String product){
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    // 获得价格
    public double getPrice(String product){
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsyn(String product){ // CompletableFuture 实现了Future
        CompletableFuture<Double> futurePrice = new CompletableFuture<>(); // 声明异步线程
        new Thread(()->{ // 执行异步线程
            try{  // 优化1. 声明异常 运行错误时抛出异常 防止调用线程 等待太久
                double price = calculatePrice(product);
                futurePrice.complete(price); // 异步线程 返回future接收值
            }catch(Exception exx){
                exx.printStackTrace();
            }

        }).start();
        return futurePrice;
    }
    // 优化2. 使用工厂方法创建CompletableFuture对象
    public Future<Double> getPriceAsynFacMeth(String product){ // CompletableFuture 实现了Future

        return CompletableFuture.supplyAsync(()->calculatePrice(product)); // 这里就直接开启一个异步线程 同时包含异常抛出
    }

    public static void main(String[] args) {
        AsynTest shop = new AsynTest("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyn("My favorite product");
        long invocationTime = (System.nanoTime() - start)/1_000_000;
        System.out.println("Invocation returned after " + invocationTime + "msecs");

        // 异步执行其他任务
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            double price = futurePrice.get();  // 阻塞等待异步线程执行完成返回值 可以设置默认等待时间 或者 上述异步线程抛出异常 来防止无限等待
            System.out.printf("Price is %.2f%n", price); // 格式化输出
        }catch (Exception e){
            e.printStackTrace();
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after " + retrievalTime + " msecs");

    }

}
