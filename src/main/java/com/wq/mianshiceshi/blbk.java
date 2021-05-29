package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class blbk {
    public static class ABPair{
        public int a;
        public int b;
        public ABPair(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        // String[] inputs = br.readLine().split(" ");
        int [] a = {6,1,4};
        int [] b = {0,6,3};
        int n = 6;
        int [][] dp = new int[a.length+5][n+5];
        List<ABPair> abList = new ArrayList<>();
        for(int i=0;i<a.length;i++){
            abList.add(new ABPair(a[i], b[i]));
        }
        abList.sort((pre, post) -> post.a - pre.a);
        int bmax = abList.get(0).b;
        int count = 1;
        int res = abList.get(0).a;
        int i=1;
        for(ABPair abp: abList){
            System.out.println(abp.a + " " + abp.b);
        }
        while(count<n){
            if(i>=a.length){
                res+=bmax;
            }
            else if(abList.get(i).a>bmax){
                res += abList.get(i).a;
                bmax = Math.max(bmax, abList.get(i).b);
                i++;
            }else if(abList.get(i).a + abList.get(i).b>2*bmax){
                System.out.println(bmax);
                res = res - bmax + abList.get(i).a + abList.get(i).b;
                bmax = Math.max(bmax, abList.get(i).b);
                i++;
            }else{
                res += bmax;
                System.out.println("b数组中:" + bmax);
            }
            count++;
        }
        System.out.println(res);

    }
}
