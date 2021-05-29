package com.wq.javashizhan.parallelDemo;

public class WordCounter {
    private final int counter;
    private final boolean lastSpace;
    public WordCounter(int counter, boolean lastSpace){
        this.counter = counter;
        this.lastSpace = lastSpace;
    }
    public WordCounter accumulate(Character c){
        if(Character.isWhitespace(c)){
            return lastSpace ? this : new WordCounter(counter, true); // true标记上一个字符是空格
        }else{
            // if(lastSpace==true)System.out.println("新扫描到单词:"+(counter+1));
            return lastSpace ? new WordCounter(counter+1,false) : this;
        }
    }
    public WordCounter combine(WordCounter wc){
        // System.out.println("combine函数:" + counter);
        return new WordCounter(counter + wc.counter, true);
    }
    public int getCounter(){
        return this.counter;
    }
}
