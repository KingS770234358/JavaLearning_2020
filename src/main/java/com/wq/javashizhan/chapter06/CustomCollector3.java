package com.wq.javashizhan.chapter06;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class CustomCollector3
        implements Collector<Integer, Map<Boolean, List<Integer>>,Map<Boolean, List<Integer>>> {
    @Override
    public Supplier<Map<Boolean,List<Integer>>> supplier(){
        return ()->new HashMap<Boolean, List<Integer>>(){
            {
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }
        };
    }
    @Override // 单个计算
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator(){
        return (Map<Boolean, List<Integer>> acc, Integer candidate)->{
            acc.get( isPrime(acc.get(true), candidate) ).add(candidate);
        };
    }
    @Override // 合并计算结果
    public BinaryOperator<Map<Boolean,List<Integer>>> combiner(){
        return (Map<Boolean,List<Integer>> map1, Map<Boolean,List<Integer>>map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }
    @Override // 整理得出最后的计算结果
    public Function<Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> finisher(){
        return Function.identity();
    }
    @Override
    public Set<Characteristics> characteristics(){
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }
    public static boolean isPrime(List<Integer> primes, int candidate){
        int end = (int)Math.sqrt((double)candidate);
        // 一开始数组为空的时候 这里也是返回 true 加入 第一个质数 2
        return primes.stream().takeWhile(i->i<=end).noneMatch(i->candidate%i==0);
    }

    public static void main(String[] args) {
        Map<Boolean, List<Integer>> res = IntStream.rangeClosed(2,20).boxed()
                .collect(new CustomCollector3());
        System.out.println(res);
    }
}
