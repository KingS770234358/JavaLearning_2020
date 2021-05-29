package com.wq.leetcodeDK;

public class mst1611tsb {
    public static void main(String[] args) {
        int shorter = 1;
        int longer = 2;
        int k = 3;
        // 特殊情况1 k<=0
        if(k<=0) System.out.println(new int [0]);
        // 特殊情况2 shorter == longer 两块板长度一样 最终组成的长度只有一种
        if (shorter == longer) {
            System.out.println(new int[]{shorter * k}); ;
        }
        int [] res = new int [k+1];
        for(int i=0;i<=k;i++){
            res[i] = (k - i) * shorter + i * longer;
        }
        for(int r: res){
            System.out.println(r);
        }

    }
}
