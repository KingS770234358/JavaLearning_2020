package com.wq.javashizhan.chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

//                                       流中输入参数的类型  A中间结果集
public class CustomCollector2 implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {
    @Override // Supplier<T> 返回一个 T
    public Supplier<Map<Boolean,List<Integer>>> supplier(){
        return () -> new HashMap<Boolean, List<Integer>>(){ // 这行用的是List<Integer>
            {
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }
        };
    }
    @Override // BiConsumer<T, a>   (T,a)->void
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator(){
        return (Map<Boolean, List<Integer>> acc, Integer candidate)->{
            // 用当前结果集中 是质数的那部分列表 去除candidate 来判断candidate是否是质数
            // 不是质数返回false放入不是质数的那一区 是质数返回true放入是质数的那一区
            acc.get( isPrime(acc.get(true), candidate) ).add(candidate);
        };
    }
    @Override // BinaryOperator<T>   (T,T) -> T
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner(){
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }
    @Override // Function<T,T>  (T,T)->T
    public Function<Map<Boolean, List<Integer>>,Map<Boolean, List<Integer>>> finisher(){
        return Function.identity(); // function恒等函数
    }
    @Override // 返回 集合set具体实现unmodifiableSet    元素是 Characteristics
    public Set<Characteristics> characteristics(){
        // EnumSet.of
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
    public static boolean isPrime(List<Integer> primes, int candidate){
        int end = (int) Math.sqrt((double)candidate);
        return primes.stream().takeWhile(i->i<=end).noneMatch(i->candidate%i==0);
    }
}
