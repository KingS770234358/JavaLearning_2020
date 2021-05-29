package com.wq.leetcodeDK;

import java.util.*;

public class gjdzrjzx1dsmpx_1356 {
    public static int oneCount(int num){
        int b = 1;
        int res = 0;
        while(num>=b){
            if((num&b)!=0) {
                res++;
            }
            b<<=1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(oneCount(2));
        int [] arr = new int [] {1024,512,256,128,64,32,16,8,4,2,1};
        List<Integer> aList = new ArrayList<>();
        for(int a: arr){
            aList.add(a);
            //System.out.println(oneCount(a));
        }
        aList.sort((a, b) -> {
           if(oneCount(a) - oneCount(b)!=0)return  oneCount(a) - oneCount(b);
           else {
               return a - b;
           }
        });
        int [] res = new int [aList.size()];
        int i=0;
        for(int a : aList){
            res[i++] = a;
            System.out.println(res[i-1]);
        }
    }
}
