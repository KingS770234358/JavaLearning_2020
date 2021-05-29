package com.wq.javashizhan.chapter04;

import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        // menu就是一个源 里面的每个元素组成的就是元素序列 后面的操作就是数据处理操作
        // 除了collect操作 其他操作都会返回另一个流;
        // 只有调用了collect操作 才会触发开始处理流水线(之前流水线上所有的操作都处在等待状态) 并返回结果集 [关闭流]
        // 通过设置 collect()中的参数 可以指定返回结果集的策略，例子中以toList()的方式返回一个List
        // Stream<String> s = menu.stream();
        // List<String> threaHighCaloricDishNames = menu.stream()
        List<String> threaHighCaloricDishNames = menu.parallelStream() // ① 获得流
                                                     .filter(dish -> dish.getCalories()>300) // ② 中间操作 筛选 stream自带的filter操作
                                                     .map(Dish::getName) // ② 中间操作 提取 stream自带的映射功能
                                                     .limit(3) // ② 中间操作 截断 stream自带的限制功能
                                                        // Collectors Stream自带收集器
                                                     .collect(Collectors.toList());// ③整合流 stream自带的收集功能
        // 流跟迭代器类似 只能遍历（消费）一次
        System.out.println(threaHighCaloricDishNames);
        /*for(String s: threaHighCaloricDishNames){
            System.out.println(s);
        }
        */
        Stream<String> top3 = menu.parallelStream().filter(dish -> dish.getCalories()>300).map(Dish::getName).limit(3);
        top3.forEach(System.out::println); // foreach 返回void的终端操作

        System.out.println("========中文=============================================");
        // 中间操作: 筛选 filter  去重 distinct/toSet  切片 takeWhile dropWhile(区间过滤筛选)
        // 截断 limit(短路求值 不处理超过大小的元素)  跳过 skip  配合使用按个数切片
        // 排序 sorted  映射 map  降维 flatMap

        // 终端操作: collect forEach count
        // 匹配 allMatch  anyMatch  noneMatch  匹配一个返回boolean值的lambda表达式(短路逻辑运算)
        // 查找 findAny 返回一个Optional<T> isPresent / isPresent(Consumer<T> block) / T get() / T orElse(T other)
        //      findFirst 返回一个Optional<T> 与findAny的差别在于 并行 的限制上 findFirst 更受限
        // 归约 reduce 给定初值 对流中的每个元素进行叠加操作，

        // 筛选、切片、映射、查找、匹配、归约
        // 数值流、多源流、无限流
        menu.parallelStream().takeWhile(dish -> dish.getCalories()>300).map(Dish::getName).forEach(System.out::println);
        System.out.println("=====================================================");
        menu.parallelStream().dropWhile(dish -> dish.getCalories()>300).map(Dish::getName).forEach(System.out::println);
        menu.parallelStream().map(Dish::getName).map(String::length).forEach(System.out::println);
        menu.parallelStream().map(Dish::getName).forEach(System.out::println);
        // Arrays::stream 把一个数组转换成单独的流
        List<String> words = Arrays.asList("hello","world");
        //             执行到这一步变成 Stream<String[]> 流中的每个元素都是一个字符串数组
        //                                        将每个字符串数组变成单独的流
        words.stream().map(s->s.split("")).map(Arrays::stream).distinct().forEach(System.out::println);
        //                                        将各个单独的流扁平化为单个流
        words.stream().map(s->s.split("")).flatMap(Arrays::stream).distinct().forEach(System.out::println);
        System.out.println("=====================================================");
        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);
        //                         会生成 {[1,3], [1,4]}  {[2,3],[2,4]} {[3,3],[3,4]} 三个流Stream<Stream<Integer[]>> 需要用flatMap扁平化
        List<int []> res = nums1.stream().flatMap(i->nums2.stream().map(j -> new int[]{i, j} )).collect(Collectors.toList());
        for (int []r : res){
            System.out.println(r[0]+" "+r[1]);
        }
        System.out.println("=====================================================");
//        List<int []> res3 = nums1.stream().flatMap(i->nums2.stream().map(j -> new int[]{i, j} ))
//                .filter(pair -> (pair[0]+pair[1])%3==0).collect(Collectors.toList());
        // 上下两种都行 过滤出 和为3的倍数的数对
        List<int []> res3 = nums1.stream().flatMap(i->nums2.stream().filter(j->(i+j)%3==0).map(j -> new int[]{i, j} )).collect(Collectors.toList());
        for (int []r : res3){
            System.out.println(r[0]+" "+r[1]);
        }
        // reduce 归约 求数组累加和 求数组累积
        // [求数组累积]
        int product = nums1.stream().reduce(1,(a,b)-> a*b); // a 是前i个元素的结果
        // int sum = nums1.stream().reduce(0,(a,b)-> a+b); // a 是前i个元素的结果
        // [求数组累加和]
        int sum = nums1.stream().reduce(0,Integer::sum); // a 是前i个元素的结果
        // 在不设置初始值的情况下 返回Option<T>对象 以第一个元素为初始值 如果流中不存在元素 则返回空 因此用Optional<T>对象接收
        Optional<Integer> sumo = nums1.stream().reduce((a,b)-> a*b);
        System.out.println(product + " " + sum + " " + sumo.get());
        // [求最小值]
        Optional<Integer> min = nums1.stream().reduce((a,b)-> a<b? a:b);
        //Optional<Integer> min = nums1.stream().reduce(Integer::min);
        System.out.println(min.get());
        // [求最大值]
        Optional<Integer> max = nums1.stream().reduce((a,b)-> a>b? a:b);
        //Optional<Integer> max = nums1.stream().reduce(Integer::max);
        System.out.println(max.get());
        // [统计数量]
        System.out.println(nums1.stream().count());
        System.out.println(nums1.stream().map(d->1).reduce((a,b)->a+b).get()); // map-reduce模式进行计数 容易[并行化]

        // 特化流(拆箱) mapToInt(lambda表达式).min/max/average/boxed 装箱转换回对象流
        // OptionalInt OptionalDouble OptionalLong 特化流对象 .orElse(1) 设置默认值为1  与上述特化流拆箱搭配使用

        // 数值流 IntStream LongStream range/rangeClosed(1,1000) 生成范围内的数字 开区间/闭区间
        // IntStream只能为流中的每个元素返回一个int，要使用它的mapToObj方法转换成对象值流

    }
}
