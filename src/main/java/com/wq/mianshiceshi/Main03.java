package com.wq.mianshiceshi;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.valueOf(sc.nextLine());
        for(int i = 0; i<T; i++){
            int N = Integer.valueOf(sc.nextLine());
            String[] flags = sc.nextLine().split("");
            PriorityQueue<Integer> one = new PriorityQueue<>();
            LinkedList<Integer> zero = new LinkedList<>();
            for(int j=0; j<N; j++){
                if(flags[j].equals("0"))zero.add(j);
                else if(flags[j].equals("1"))one.add(j);
            }
            int M = Integer.valueOf(sc.nextLine());
            String[] sexs = sc.nextLine().split("");
            int [] res = new int[M];
            for(int j=0; j<M; j++){
                boolean flag = false;
                if(sexs[j].equals("M")){
                    if(one.size()!=0){
                        int k = one.poll();
                        res[j] = k;
                    }else{
                        int k = zero.getFirst();
                        res[j] = k;
                        zero.removeFirst();
                        one.add(k);
                    }
                }else{
                    if(zero.size()!=0){
                        int k = zero.getFirst();
                        res[j] = k;
                        zero.removeFirst();
                        one.add(k);
                    }else{
                        int k = one.poll();
                        res[j] = k;
                    }
                }
                System.out.println(res[j] + 1);
            }
        }
    }
}
