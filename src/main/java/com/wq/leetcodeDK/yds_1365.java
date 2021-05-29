package com.wq.leetcodeDK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class yds_1365 {
    public static class Pair{
        public int val;
        public int idx;
        public int less;
        public int same;
        public Pair(int val, int idx){
            this.idx = idx;
            this.val = val;
            this.less = 0;
            this.same = 0;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) {
        int [] nums = {8,1,2,2,3};
        int [] res = new int[nums.length + 1];
        List<Pair> pairList = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            pairList.add(new Pair(nums[i], i));
        }
        pairList.sort((a, b)->a.val-b.val);
        pairList.get(0).less = 0;
        int same = 1;
        for(int i=1;i<nums.length;i++){
            if(pairList.get(i).val>pairList.get(i-1).val){
                pairList.get(i).less = pairList.get(i-1).less + same;
                same = 1;
            }else if(pairList.get(i).val==pairList.get(i-1).val){
                pairList.get(i).less = pairList.get(i-1).less;
                same ++;
            }
        }
        pairList.sort((a, b)->a.idx-b.idx);
        for(int i=0; i<pairList.size();i++){
            res[i] = pairList.get(i).less;
        }
        for(int r: res){
            System.out.println(r);
        }
        //System.out.println(res);
    }
}
