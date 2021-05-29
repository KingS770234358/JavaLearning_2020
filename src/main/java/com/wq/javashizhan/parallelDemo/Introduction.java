package com.wq.javashizhan.parallelDemo;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Introduction {
    public static void main(String[] args) {
        // 求1~n的和
        int n = 5000000;
        long now = System.currentTimeMillis();
        long res = Stream.iterate(1L, i->i+1) // 生成自然数无限流 也可以使用 IntStream.rangeClosed(1,n);
                .limit(n)                // 限制在n个内
                .reduce(0L, Long::sum);// 用求和归约流中的元素
        System.out.println("iterate方式顺序执行消耗时间: " + (System.currentTimeMillis()-now));
        //System.out.println(res);
        // 并行
        now = System.currentTimeMillis();
        long resParallel = Stream.iterate(1L, i->i+1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println("iterate方式并行执行消耗时间: " + (System.currentTimeMillis()-now));
        /*
        * 上述并行化
        * 1) iterate 生成的是装箱的对象，进行reduce的时候需要拆箱
        * 2) iterate 每次计算都要依赖前一次的计算结果，不易并行化
        * ===> 整体上增加了开销
        * 改进：可以使用 LongStream.rangeClosed生成指定范围的数据 容易分成独立的小块并行
        * 是原始类型long, 不需要装箱 拆箱
        * */
        now = System.currentTimeMillis();
        long resParallel3 = LongStream.range(1, n)
                .reduce(0L,Long::sum);
        System.out.println("LongStream方式顺序执行消耗时间: " + (System.currentTimeMillis()-now));
        now = System.currentTimeMillis();
        long resParallel2 = LongStream.range(1, n)
                .parallel()
                .reduce(0L,Long::sum);
        System.out.println("LongStream方式并行执行消耗时间: " + (System.currentTimeMillis()-now));

    }

}
