package com.wq.mianshiceshi;

import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main05 {
    public static class Node{
        public int id;
        public List<Integer> sons;
        public Node(int id){
            this.id = id;
            this.sons = new ArrayList<>();
        }
        public void addSon(int son){
            sons.add(son);
        }
    }
    public static Map<Integer, Node> nodeMap = new HashMap<>();
    public static void dfs(Node node){
        if(node.sons.size()==0)return;
        for(int i=0; i< node.sons.size();i++){
            System.out.println(node.sons.get(i));
            dfs(nodeMap.get(node.sons.get(i)));
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        Map<Integer, Integer> count = new HashMap<>();
        // System.out.println(n);
        Node sourceNode = new Node(1);
        nodeMap.put(1, sourceNode);
        for(int i = 2; i<= n; i++){
            int A = sc.nextInt();
            count.put(A, count.getOrDefault(A, 0) + 1);
//            Node tmp = new Node(i);
//            nodeMap.put(i, tmp);
//            nodeMap.get(A).sons.add(i);
        }
        // dfs(sourceNode);
    }
}
