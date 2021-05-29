package com.wq.javashizhan.chapter06;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

/*
* 自定义收集器 区分开 2~n之间的质数 和 非质数
* 通过除以现有的质数 判断是否为质数
* */
//                                                  参数      中间结果集 A                最终结果集R
public class CustomCollector
        implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean,List<Integer>>> {
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier(){
        return ()->new HashMap<Boolean, List<Integer>>(){
            {
                put(true, new ArrayList<Integer>());
                put(false, new ArrayList<Integer>());
            }
        };
    }
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator(){
        return (Map<Boolean, List<Integer>> acc, Integer candidate)->{
          acc.get( isPrime( acc.get(true), candidate) )  // 用candidate除以 acc.get(true)中已经存的质数
             .add(candidate);
        };
    }
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner(){
        return ((Map<Boolean, List<Integer>> map1,Map<Boolean, List<Integer>> map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        });
    }
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher(){
        return Function.identity();
    }
    @Override
    public Set<Collector.Characteristics> characteristics(){
        return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
    }
    public static boolean isPrime(List<Integer> primes,int candidate){
        int end = (int)Math.sqrt((double) candidate);
        if(primes.size()==0){
            System.out.println(primes);
            System.out.println(primes.stream()
                    .takeWhile(i->i<=end) // 当访问到大于end的结果及时停止
                    .noneMatch(i -> candidate%i==0));
        }
        return primes.stream()
                .takeWhile(i->i<=end) // 当访问到大于end的结果及时停止
                .noneMatch(i -> candidate%i==0);
    }

    public static void main(String[] args) {
        Map<Boolean,List<Integer>> res = IntStream.rangeClosed(2, 20).boxed().collect(new CustomCollector());
        System.out.println(res);
    }
}
