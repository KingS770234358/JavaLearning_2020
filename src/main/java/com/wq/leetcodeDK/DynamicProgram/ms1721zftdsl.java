package com.wq.leetcodeDK.DynamicProgram;

public class ms1721zftdsl {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        if(height.length<3) System.out.println(0);;
        int l = 0, r = height.length - 1;
        int lMax = height[l], rMax = height[r]; // 访问之前先更新最大值
        int res = 0;
        while(l<r){
            if(rMax > lMax){ // 以短边装水
                res += lMax - height[l++]; // 被装水之后才会移动
                lMax = Math.max(lMax, height[l]);// 访问之前先更新最大值
            }else{
                res += rMax - height[r--];
                rMax = Math.max(rMax, height[r]);
            }
        }
        System.out.println(res);
    }
}
