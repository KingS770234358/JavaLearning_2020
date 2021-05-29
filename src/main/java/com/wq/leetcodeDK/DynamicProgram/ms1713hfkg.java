package com.wq.leetcodeDK.DynamicProgram;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ms1713hfkg {
    public static void main(String[] args) {
        String [] dictionary = {"looked","just","like","her","brother"};
        Set<String> dictSet = new HashSet<>(Arrays.asList(dictionary));
        String sentence = "jesslookedjustliketimherbrother";
        int n = sentence.length();
        int [] dp = new int[n+1]; // dp[0] = 0; 表示前i个字符最少多少个字符未识别
        for(int i = 1; i<=n; i++){
            dp[i] = dp[i-1] + 1;
            for(int j = 0; j<i; j++){
                // 遍历所有以 第 i 个字符结尾的 子串
                if(dictSet.contains(sentence.substring(j, i))){
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
            // System.out.println(dp[i]);
        }
        System.out.println(dp[n]);
    }
}
