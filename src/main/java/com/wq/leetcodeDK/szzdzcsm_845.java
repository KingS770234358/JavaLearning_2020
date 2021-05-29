package com.wq.leetcodeDK;

public class szzdzcsm_845 {
    public static void main(String[] args) {
        int [] A = {0,2,2};
        int start = 1;
        while(start<A.length&&A[start]<A[start-1]){
            start++;
        }
        int length = 0;
        int curl = 1;
        for(int i=start;i<A.length;){
            while(i<A.length&&A[i]==A[i-1]){
                i++;
            }
            boolean up = false;
            boolean down = false;
            while(i<A.length&&A[i]>A[i-1]){
                curl++;
                i++;
                up = true;
            }
            while(i<A.length&&A[i]<A[i-1]){
                curl++;
                i++;
                down = true;
            }
            if(up&&down&&curl>length){
                length = curl;
            }
            curl = 1;
        }
        System.out.println(length);
    }
}
