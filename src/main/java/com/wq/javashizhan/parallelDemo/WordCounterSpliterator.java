package com.wq.javashizhan.parallelDemo;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> { // Character是Spliterator遍历元素的类型

    private final String string; // Spliterator 要遍历的数组
    private int currntCharPos = 0; // 当前遍历到的位置 初始是0（可以用来计算还剩余多少需要遍历
    public WordCounterSpliterator(String string){
        this.string = string;
    }
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) { // 传入处理元素的 消费者函数式接口
        action.accept(string.charAt(currntCharPos++)); // 处理当前元素
        return currntCharPos<string.length(); // 如果遍历完当前的元素 还小于string.length() 说明还有元素需要遍历
    }
    @Override
    public Spliterator<Character> trySplit(){ // 将元素区间划分一部分出去 交给另外一个Spliterator去遍历处理
        int remainSize = string.length() - currntCharPos; // 剩余可以用来划分的元素数量
        if(remainSize<5) return null; // 如果剩余的元素数量小于 5 的话 就不需要交给另一个Spliterator处理
        // 从剩余的部分的中点开始测试 是否可以以该点进行划分
        for(int splitPos = currntCharPos + remainSize/2; splitPos<string.length(); splitPos++){
            if(Character.isWhitespace(string.charAt(splitPos))){ // 当前探测划分点是空格 可以用于划分
                Spliterator<Character> subSpliterator = new WordCounterSpliterator(string.substring(currntCharPos, splitPos));
                // 既然 currntCharPos, splitPos 之间的元素交给 另外一个Spliterator处理了 当前Spliterator要处理的元素就跳过这些
                currntCharPos = splitPos; // 当划分至不能再划分了 当前位置就固定了 剩余部分就固定了 该Spliterator要处理的剩余大小就固定了
                return subSpliterator;
            }
        }
        return null;// 默认返回null 即不需要进行划分交给另一个Spliterator处理
    }
    @Override
    public long estimateSize(){
        return string.length() - currntCharPos;
    }
    @Override
    public int characteristics(){
        // 大小已知源 遵循源顺序遍历 由其拆分出来的Spliterator都是SIZED 遍历的元素非空 元素不可更改
        return SIZED + ORDERED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
