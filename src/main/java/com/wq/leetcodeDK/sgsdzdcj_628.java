package com.wq.leetcodeDK;

import java.util.Arrays;

public class sgsdzdcj_628 {
    public static void main(String[] args) {
        int []nums = {1,2,3,4};
        Arrays.sort(nums);
        int max = Math.max(nums[0]*nums[1]*nums[2], nums[nums.length-3]*nums[nums.length-2]*nums[nums.length-1]);
        max = Math.max(max, nums[0]*nums[1]*nums[nums.length-1]);
        System.out.println(max);
    }
}
