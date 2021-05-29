package com.wq.leetcodeDK;

import java.util.ArrayList;
import java.util.List;

public class crqj_57 {
    public static void main(String[] args) {
        int [][] intervals = new int[][]{{1,3},{6,9}};
        int [] newInterval = new int[]{2,5};
        boolean f = false;
        int left = newInterval[0];
        int right = newInterval[1];
        List<int[]> tmpList = new ArrayList<>();
        for(int[] interval : intervals){
            if(interval[0]>right){
                if(f==false){
                    tmpList.add(new int[]{left, right});
                }
                tmpList.add(interval);
            }else if(interval[1]<left){
                tmpList.add(interval);
            }else{
                left = Math.min(interval[0], left);
                right = Math.max(interval[1], right);
            }
        }
        if(!f){
            tmpList.add(new int[]{left, right});
        }
        int [][] ans = new int[tmpList.size()][2];
        int i=0;
        for(int[] t: tmpList){
            ans[i++] = t;
        }
        System.out.println(ans);
    }
}
