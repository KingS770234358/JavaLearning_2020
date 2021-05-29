package com.wq.javashizhan.parallelDemo;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterSpliterator2 implements Spliterator<Character> {
    private final String string; // 固定要遍历的数组
    private int currentPos = 0; // 当前遍历到的位置

    // 构造函数
    public WordCounterSpliterator2(String string){
        this.string = string;
    }

    // 实现 处理当前元素的方法 返回Boolean标志是否还有元素需要遍历
    @Override // 因为是要处理元素 也就是在消费元素 所以需要一个Consumer 然后这个Consumer可以【接受】处理的是数组中元素的上界
    public boolean tryAdvance(Consumer<? super Character> action){
        action.accept(string.charAt(currentPos++)); // 这里是接受accept处理。。
        return currentPos < string.length(); // 是否还有元素需要处理
    }
    // 实现 尝试划分出部分元素给另一个Spliterator处理的方法
    @Override
    public Spliterator<Character> trySplit(){
        // 当前剩余元素的数量
        int currentSize = string.length() - currentPos;
        if(currentSize < 10) return null; // 剩余元素数量少于一定程度 就不再划分出去
        for(int splitPos = currentPos + currentSize/2 ; splitPos < string.length(); splitPos++){
            if(Character.isWhitespace(string.charAt(splitPos))){ // 尝试在 splitPos 出进行划分 如果这个位置是空格 则可以作为划分的位置
                Spliterator<Character> spliterator = new WordCounterSpliterator2(string.substring(currentPos, splitPos));
                // 这里一定要缩小当前Spliterator需要遍历的元素范围 前面的都被划分出去了！
                currentPos = splitPos;
                return spliterator;
            }
        }
        return null; // 返回null 表示无需继续拆分
    }
    // 当前Spliterator要处理的元素个数
    @Override
    public long estimateSize(){
        return string.length() - currentPos;
    }
    @Override // 特性编码
    public int characteristics(){
        return SIZED + SUBSIZED + ORDERED + IMMUTABLE + NONNULL;
    }
    static String SENTENCE = " Nel    mezzo  del Caamin   di nostra   vita "+
            " nm ri  in una   ssel v  we";
    public static void main(String[] args) {
        Spliterator<Character> spliterator = new WordCounterSpliterator2(SENTENCE);
        // 使用 StreamSupport.stream 工厂方法  第二个参数 true 创建 一个 并行流
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        WordCounter wc = stream.reduce(new WordCounter(0,true),
                WordCounter::accumulate,
                WordCounter::combine);
        System.out.println(wc.getCounter());
    }
}
