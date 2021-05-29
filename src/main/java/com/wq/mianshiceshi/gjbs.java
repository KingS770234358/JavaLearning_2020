package com.wq.mianshiceshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class gjbs {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        String str = br.readLine();
        int[][] dp = new int [str.length()+5][str.length()+5];// dp[i][j]表示 i j之间不同字符的个数
        Map<Character, Integer> counter = new HashMap<>(); // 标记每个是否出现过
        int count = 0;// 对串中包含的字符个数进行计数
        for(int i = 0;i<str.length();i++){
            dp[i][i] = 1;
            if(counter.getOrDefault(str.charAt(i), 0)==0){
                count++;
                counter.put(str.charAt(i), 1);
            }
        }

        int sIndex = -1; // 起始位置设置成1
        String res = ""; // "zzz...." 100个z 字典序肯定是最大的
        for(int i=0;i<100;i++){
            res += "z";
        }

        for(int i=0;i<str.length();i++){
            Map<Character, Integer> charCount = new HashMap<>(); // i j中 每个字符出现的次数
            charCount.put(str.charAt(i), 1);
            for(int j=i+1;j<str.length();j++){
                Character curChar = str.charAt(j);
                if(charCount.getOrDefault(curChar, 0)==0){// 当前字符在以i开头的子串中未出现
                    dp[i][j] = dp[i][j-1] + 1;// i j 之间不同字符的个数 比i j-1之间字符的个数多1
                    charCount.put(curChar, 1); // 将当前字符设置为已出现
                }else{
                    dp[i][j] = dp[i][j-1];
                }
                if(dp[i][j]==count && res.compareTo(str.substring(i,j+1))>=0 && i>sIndex){
                    sIndex = i;
                    res = str.substring(i,j+1);
                }
            }
        }
        if(sIndex==-1){
            System.out.println(0+" "+str.length());
        }else{
            System.out.println(sIndex + " " + res.length());
        }
    }
}
