package com.wq.leetcodeDK;

import java.util.*;

public class dcjl_127 {

    // 词表
    public static Map<String, Integer> wordId = new HashMap<String, Integer>();
    // 距离为1的词关系
    public static List<List<Integer>> edge = new ArrayList<List<Integer>>();
    // 词表大小
    public static int nodeNum = 0;

    // 将单词加入词表
    public static void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }
    // 加入关系边
    public static void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            // 虚拟节点思想 因此 每对距离为1的单词之间会变成 word1 -> 虚拟 -> word2 距离变两倍
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log"};

        // 建立 wordList 的词表图
        for(String word: wordList){
            dcjl_127.addEdge(word);
        }
        // 将 beginWord 加入词表图
        addEdge(beginWord);

        // 判断结束词是否在图中
        if (!wordId.containsKey(endWord)) {
            System.out.println(0);
        }else{
            int [] dis = new int[nodeNum]; // nodeNum包括虚拟节点
            Arrays.fill(dis, Integer.MAX_VALUE);
            int beginWordId = wordId.get(beginWord);
            int endWordId = wordId.get(endWord);
            dis[beginWordId] = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(beginWordId);
            while(!queue.isEmpty()){
                int x = queue.poll();
                if(x==endWordId){
                    System.out.println(dis[endWordId]/2 + 1);
                }
                for(int it :edge.get(x)){
                    if(dis[it] == Integer.MAX_VALUE){
                        dis[it] = dis[x] + 1;
                        queue.offer(it);
                    }
                }
            }
            System.out.println(0);
        }
    }

}
