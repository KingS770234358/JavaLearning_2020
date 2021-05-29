package com.wq.mianshiceshi;

import java.io.*;
import java.util.*;

public class ykhkws {

    public static class Node{
        public int value;
        public Node next;
        public Node(){
        }
        public Node(int value){
            this.value = value;
            this.next = null;
        }
        public void setNext(Node next){
            this.next = next;
        }
    }
    public static Node revers(Node head){
        Node pre = null;
        Node cur = head;
        Node next = head;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)  throws IOException {
        Node head = new Node(1);
//        Node n1 = new Node(2);
//        head.setNext(n1);
//        Node n2 = new Node(3);
//        n1.setNext(n2);
//        Node n3 = new Node(4);
//        n2.setNext(n3);
//        Node n4 = new Node(5);
//        n3.setNext(n4);
        Node cur = head;
        for(int i=2;i<10;i++){
            Node n = new Node(i);
            cur.next = n;
            cur = n;
        }
        Node res = revers(head);
        cur = res;
        while(cur!=null){
            System.out.print(cur.value+" ");
            cur = cur.next;
        }
        List<Integer> list = new LinkedList<>();

    }
}
