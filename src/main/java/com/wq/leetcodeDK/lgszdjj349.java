package com.wq.leetcodeDK;

import java.lang.reflect.Array;
import java.util.*;

public class lgszdjj349 {
    public static void main(String[] args) {
        int [] nums1 = {1,2,2,1};
        int [] nums2 = {2,2};
        Set<Integer> iSet = new HashSet<>();
        Set<Integer> rSet = new HashSet<>();
        for(int i=0; i<nums1.length; i++){
            if(!iSet.contains(nums1[i])){
                iSet.add(nums1[i]);
            }
        }
        for(int i=0; i<nums2.length; i++){
            if(iSet.contains(nums2[i])){
                rSet.add(nums2[i]);
            }
        }
        int [] res = new int[rSet.size()];
        int idx = 0;
        for(int a: rSet){
            res[idx++] = a;
        }

    }
}
