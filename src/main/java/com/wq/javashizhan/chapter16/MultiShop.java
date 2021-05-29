package com.wq.javashizhan.chapter16;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MultiShop {
    private static List<AsynTest> shops = List.of(
            new AsynTest("BestPrice"),
            new AsynTest("LetsSaveBig"),
            new AsynTest("MyFavoriteShop"),
            new AsynTest("BuyItAll"),
            new AsynTest("Best"),
            new AsynTest("Lets"),
            new AsynTest("My"),
            new AsynTest("Buy"),
            new AsynTest("Price"),
            new AsynTest("SaveBig"),
            new AsynTest("FavoriteShop"),
            new AsynTest("ItAll")
    );

    // 1、使用顺序查询所有商店的方式实现的 findPrice 方法  查询每家商店中product的价格
    public static List<String> findPrice(String product){
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    // 2、使用并行流查询所有商店的方式实现的 findPriceParallel  查询每家商店中product的价格
    public static List<String> findPriceParallel(String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    // 3、使用CompletableFuture发起异步请求
    public static List<String> findPriceCompletabelFuture(String product){
        List<CompletableFuture<String>>  tasks = shops.stream()
                .map(shop-> CompletableFuture.supplyAsync( // 将shop map成返回 String的线程
                        ()-> shop.getName() + " Price is " + shop.getPrice(product)))
                .collect(Collectors.toList());

        // 如果将这两个流串成一个单一流 流的延迟特性会使其变成同步顺序执行  354 面图解
        // 单个流 因为要返回一个List<String> 因此 需要等所有future执行完毕 将其包含的值取出来 填充到列表里 才能返回

        // 这里使用两个流分开    首先是一个shops流 变成异步线程流 开启一个异步线程即返回
        //                     然后异步线程流 变成异步线程执行后的结果
        return tasks.stream()
                // .map(task->task.join())
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // 4. 定制的执行器 使用CompletableFuture发起异步请求
    private static final Executor executor =
            Executors.newFixedThreadPool(Math.max(shops.size(), 800),
                    (Runnable r)->{
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        return t;
                    });
    public static List<String> findPriceCustomExecutor(String product){
        List<CompletableFuture<String>>  tasks = shops.stream()
                .map(shop-> CompletableFuture.supplyAsync( // 将shop map成返回 String的线程
                        ()-> shop.getName() + " Price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());
        return tasks.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // 验证方法1 单行流 的执行性能
        long start = System.nanoTime();
        System.out.println(findPrice("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证方法2 并行流 的执行性能
        start = System.nanoTime();
        System.out.println(findPriceParallel("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证方法3 CompletableFuture异步线程 的执行性能
        start = System.nanoTime();
        System.out.println(findPriceCompletabelFuture("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");

        // 验证方法4 CompletableFuture异步线程 + 定制执行器 的执行性能
        start = System.nanoTime();
        System.out.println(findPriceCustomExecutor("myPhone27S"));
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }
}
