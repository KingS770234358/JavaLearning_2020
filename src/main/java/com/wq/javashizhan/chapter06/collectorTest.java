package com.wq.javashizhan.chapter06;

import com.wq.javashizhan.chapter04.Dish;
import javafx.css.Match;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/*
* collect()中能这么写
* */

public class collectorTest {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 菜单
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
        /*
        * collect
        *
        * counting maxBy summingInt summarizingInt
        * joining
        *
        * reducing
        * groupingBy filtering mapping groupingBy counting maxBy summingInt summarizingInt
        * */
        System.out.println("============================== 数据统计 =============================");
        // [计数] Collectors.counting() 查看菜的数量
        System.out.println(menu.stream().collect(Collectors.counting()));
        System.out.println(menu.stream().count());

        // import static java.util.stream.Collectors.*;

        // [最大 最小值] maxBy minBy 接受Comparator参数
        // 通过 Comparator.comparingInt 找到要排序的字段属性
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxCalories = menu.stream().collect(Collectors.maxBy(dishCaloriesComparator)); // 这里可能为空 返回一个Optional泛型
        System.out.println(maxCalories.get());

        // [汇总-聚合函数] summingInt summingLong summingDouble averagingInt averagingDouble averagingLong
        System.out.println(menu.stream().collect(Collectors.summingInt(Dish::getCalories)));
        // IntSummaryStatistics DoubleSummaryStatistics LongSummaryStatistics 用于数据统计：计数、总和、最大、最小、平均
        IntSummaryStatistics iss = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(iss);

        System.out.println("==================================连接字符串===================================");
        // joining() 拼接字符串 // 中间可以定义分隔符 // joining() 使用StringBuilder来把生成的字符串逐个追加起来
        System.out.println(menu.stream().map(Dish::getName).collect(Collectors.joining(" ")));
        // menu.stream().collect(Collectors.joining(" ")) // 会把Dish toString的结果拼接起来

        System.out.println("==================================广义归约 - reducing===================================");
        // 计算总热量 reducing(归约操作起始值, 要归约的东西-对传入的东西做一些转换, 归约的方法)
        // 注意：归约的方法：BinaryOperator<T>===BiFunction<T,T,T> 返回的值 要跟传入的两个值 是同一个类型的
        // Collectors.counting()   ===   Collectors.reducing(0L, e->1, Long::sum)
        System.out.println(menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (i,j) ->  i+j)));
        System.out.println(menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::sum)));
        // 这里直接写上 归约的方法 返回一个Optional<T>对象
        System.out.println(menu.stream().map(Dish::getCalories).reduce(Integer::sum).get());
        System.out.println(menu.stream().mapToInt(Dish::getCalories).sum()); // 比较好的方法 避免自动拆箱
        Optional<Dish> maxC = menu.stream().collect(Collectors.reducing((a,b) ->  a.getCalories()<b.getCalories()? a : b));
        System.out.println(maxC.get());

        System.out.println("==================================分组 groupingBy===================================");
        /*
        * groupingBy(分组标准, 组内收集器)
        * 配合 filtering/mapping/filterMapping/groupingBy/
        * counting/maxBy/collectingAndThen/summingInt
        *
        * */
        // groupingBy返回一个map 键：用于分组的属性 值：在该属性上取值相同的对象的集合
        // 这里的groupingBy收集器 单参数：Dish::getType, 但是后面是隐含了另一个 Collectors.toList()收集器
        Map<Dish.Type, List<Dish>> mtd = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(mtd);
        // 分组之后 可以使用-filtering-进行过滤：先过滤 后分组的话 被过滤掉的元素不会出现在分组中；先分组后过滤可以保留被过滤掉的空集
        // filtering 接收一个谓词 和 Collector 收集谓词操作之后的元素
        Map<Dish.Type, List<Dish>> mtd1 = menu.stream().collect(Collectors.groupingBy(Dish::getType,
                // 先分组 后过滤
                Collectors.filtering(dish -> dish.getCalories()>500, Collectors.toList())));
        System.out.println(mtd1);
        // 先过滤 后分组
        Map<Dish.Type, List<Dish>> mtd2 = menu.stream().filter(dish -> dish.getCalories()>500).collect(Collectors.groupingBy(Dish::getType));
        System.out.println(mtd2);
        // 分组之后 可以使用-mapping-函数对每组内部的元素进行映射
        // mapping接收一个谓词(映射函数) 以及 一个Collector 对映射之后的元素进行收集
        // 还可以使用-filterMapping-函数合并多个流
        Map<Dish.Type, List<String>> mtd3 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));
        System.out.println(mtd3);
        // mapping连续型变量
        Map<Dish.Type, Set<CaloricLevel>> mtd33 = menu.stream().collect(Collectors.groupingBy(
                Dish::getType, Collectors.mapping(
                        dish->{
                            if(dish.getCalories()<=400) return CaloricLevel.DIET;
                            else if(dish.getCalories()<=70) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },
                        // 用toCollection方法 配合 特定集合构造函数的方法引用 返回特定类型的集合 如下返回一个HashSet
                        Collectors.toCollection(HashSet::new))));
                        //Collectors.toSet())));

        // 多级分组 先按类型进行分类  再用-groupingBy-按卡路里级别进行分类
        // Map<Dish.Type, Map<CaloricLevel, List<Dish>>> typeThenCalories =
        Map<Dish.Type, Map<CaloricLevel, List<String>>> typeThenCalories =
                menu.stream().collect(Collectors.groupingBy(Dish::getType, // 一级分组 离散变量分组
                            Collectors.groupingBy( // 二级分组 连续变量规则化分组
                                    dish->{
                                        if(dish.getCalories()<=400) return CaloricLevel.DIET;
                                        else if(dish.getCalories()<=700) return CaloricLevel.NORMAL;
                                        else return CaloricLevel.FAT;
                                    },
                                    Collectors.mapping(Dish::getName, Collectors.toList())
                            )
                        ));
        System.out.println(typeThenCalories);
        System.out.println(typeThenCalories.get(Dish.Type.FISH).get(CaloricLevel.NORMAL));
        // 传递给第一个groupingBy的第二个参数可以不是 二级分类用的groupingBy 而是别的收集器 如-counting-相当于是组内统计了
        // 注意 counting返回的是Long数据类型
        Map<Dish.Type, Long> mtd4 = menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        // 可以对分组后的组内求最大值-maxBy-
        Map<Dish.Type, Optional<Dish>> mtd5 = menu.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        // 区别中间操作的 filter map 和 收集器 filtering mapping
        // -collectingAndThen- 接收 要转换的收集器 以及 转换函数 并 返回另外一个收集器
        // 如下例子 外层CollectingAndThen收集器 接收 一个内层的collectingAndThen收集器 以及 转换函数 Dish::getName
        // 内层collectingAndThen收集器接收一个maxBy收集器 以及 Optional::get 转换函数 并将执行结果返回给外层收集器
        Map<Dish.Type, String> mtd6 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                Collectors.collectingAndThen(
                        Collectors.collectingAndThen( // 两层 collectingAndThen 由内向外执行
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        ),
                        Dish::getName)
                ));
        System.out.println(mtd5);
        System.out.println(mtd6);
        System.out.println("============分组 groupingBy 的特例 分组函数返回Boolean值 则成为分区函数================");
        // 将素菜和荤菜分开
        Map<Boolean, List<String>> mtd7 = menu.stream()
                .collect(Collectors.groupingBy(Dish::isVegetarian,
                        Collectors.mapping(Dish::getName,Collectors.toList())));
        System.out.println(mtd7);
        // 用分区判断 2~n之间的质数和非质数
        int n = 100;
        Map<Boolean, List<Integer>> primes =
                IntStream.rangeClosed(2, n).boxed() // 这里需要装箱
                        // partitioningBy只传入一个参数的情况下 默认会使用Collectors.toList()收集器返回
                        .collect(Collectors.partitioningBy(i->isPrime(i), Collectors.toList()));
        System.out.println(primes.get(true));
    }
    public static boolean isPrime(int n){
        int end = (int) Math.sqrt((double)n);
        // 这里要是rangeClosed 取到 根号n 的整数下界
        return IntStream.rangeClosed(2, end).noneMatch(i->n%i==0); // 不能被2~根号n - 1之间任何数整除 就是质数
    }

}
