package com.wq.javashizhan.parallelDemo;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorDemo {
    static String SENTENCE = " Nel    mezzo  del Caamin   di nostra   vita "+
            " nm ri  in una   ssel v  we";

    public static void main(String[] args) {
        Stream<Character> ss = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);
        // reduce中第三个函数是为了在并行的情况下 合并多个线程的执行结果用的
        WordCounter wc = ss.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        ss = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt); // 一个流 只能消费一次 需要重开
        WordCounter wc2 = ss.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println(wc.getCounter());
        System.out.println(wc2.getCounter()); // 并行的时候 划分方式不对 导致 一个单词被重复计算 结果错误

        Spliterator<Character> spliterator = new WordCounterSpliterator2(SENTENCE);
        // 通过 StreamSupport.stream 工厂方法 使用自定义的Spliterator
        // 第二个参数 true 创建 一个 并行流
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        WordCounter wc3 = stream.reduce(new WordCounter(0,true),
                WordCounter::accumulate,
                WordCounter::combine);
        System.out.println(wc3.getCounter());

    }

}
