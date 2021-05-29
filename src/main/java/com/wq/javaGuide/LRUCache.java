package com.wq.javaGuide;

import java.util.*;

public class LRUCache {
    public static class Node{
        int key;
        int value;
        Node pre;
        Node next;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    private Map<Integer, Node> map = new HashMap<>();
    private Node head = new Node(-1,-1);
    private Node tail = new Node(-1,-1);
    private int MAX_NUM;

    public void move2Head(Node node){
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public int get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.next.pre = node.pre;
            node.pre.next = node.next;
            move2Head(node);
            return node.value;
        }
        return -1;
    }
    public void set(int key, int value){
        if(get(key)!=-1){// 如果包含这个key的话 这里已经将他放至队首
            map.get(key).value = value;
        }else{
            if(map.size()==MAX_NUM){
                int rk = tail.pre.key;
                tail.pre = tail.pre.pre;
                tail.pre.next = tail;
                map.remove(rk); // 维护HashMap
            }
            Node node = new Node(key, value);
            map.put(key, node); // 维护HashMap
            move2Head(node);
        }
    }
    public int[] LRU (int[][] operators, int k) {
        // write code here
        this.MAX_NUM = k;
        head.next = tail;
        tail.pre = head;
        int getNum = (int)Arrays.stream(operators).filter(x->x[0]==2).count();
        int [] res = new int[getNum];
        for(int i=0, resi=0;i<operators.length;i++){
            if(operators[i][0]==1){
                set(operators[i][1],operators[i][2]);
            }else{
                res[resi++] = get(operators[i][1]);
            }
        }
        return res;
    }
}
