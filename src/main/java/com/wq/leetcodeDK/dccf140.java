package com.wq.leetcodeDK;

import java.util.*;

/**
 * ※※※※※※※※※※※※※※※※
 * 140. 单词拆分 II   =====  hfkg
 * */
public class dccf140 {
    public static String inputStr;
    public static Set<String> wordSet;
    public static boolean[] dp; // leetcode上这要使用boolean 不能使用Boolean（会报空指针异常
    public static void main(String[] args) {

        String s = "catsanddog";
        List<String> wordDict = Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"});
        inputStr = s;
        wordSet = new HashSet<>(wordDict); // 单词是否在词表wordSet中 === api运用1
        int len = s.length();

        // 第 1 步：动态规划计算 前i个单词 拆分成wordSet中的单词 是否有解
        // dp[i] 表示「长度」为 i 的 s 前缀子串可以拆分成 wordDict 中的单词
        dp = new boolean[len + 1]; // 长度包括 0 ，因此状态数组的长度为 len + 1
        dp[0] = true; // 0 如果一个单词正好在 wordDict 中，dp[0] 设置成 true 是合理的
        for(int i=1; i<=len; i++){
            for(int j=0; j<i; j++){
                //                                     这里dp下表是从0开始
                if(wordSet.contains(s.substring(j,i))&&dp[j]){
                    // s       0 1 2 3 4 5 6 7 ....   比如当 j=4 4 ~ 7组成的单词在wordSet中
                    // dp    0 1 2 3 4 5 6 7 8        那么就要判断 0 ~ 3串能不能被拆分 dp[4]
                    dp[i] = true;
                    break; // dp[i] 已经有解 可拆分
                }
            }
        }

        // 第 2 步：回溯算法搜索所有符合条件的解
        List<String> res = new ArrayList<>();
        if (dp[len]) {
            Deque<String> path = new ArrayDeque<>(); // === api运用2
            dfs(len, path, res);
            // return res;
        }
        // return res;
        for(String rs: res){
            System.out.println(rs);
        }
    }
    /**
     * s[0:len) 如果可以拆分成 wordSet 中的单词，把递归求解的结果加入 res 中
     * @param len     长度为 len 的 s 的前缀子串
     * @param path    从叶子结点到根结点的路径
     * @param res     保存所有结果的变量
     */
    private static void dfs(int len, Deque<String> path, List<String> res) {
        if(len == 0){
            res.add(String.join(" ", path)); // === api运用3
            return;
        }
        for(int i = len -1; i>=0; i--){
            String suffix = inputStr.substring(i, len);
            if(wordSet.contains(suffix)&&dp[i]){
                path.addFirst(suffix); // === api运用4
                dfs(i, path, res);
                path.removeFirst(); // === api运用5
            }
        }
    }
}
