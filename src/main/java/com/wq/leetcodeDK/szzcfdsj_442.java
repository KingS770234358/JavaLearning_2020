package com.wq.leetcodeDK;


import java.util.ArrayList;
import java.util.List;

public class szzcfdsj_442 {
    private static void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
//        nums[index1] = nums[index1] ^ nums[index2];
//        nums[index2] = nums[index1] ^ nums[index2];
//        nums[index1] = nums[index1] ^ nums[index2];
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }
    public static void main(String[] args) {
        int [] nums = {4,3,2,7,8,2,3,1};
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<nums.length;i++){
            while(nums[nums[i] - 1] != nums[i]){
                if(nums[i] - 1!=nums[i]){
                    int tmp = nums[nums[i] - 1];
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1){
                res.add(nums[i]);
            }
        }
        System.out.println(res);
    }
}
