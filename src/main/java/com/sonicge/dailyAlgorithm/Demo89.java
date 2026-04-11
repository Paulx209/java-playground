package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【单词拆分】
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo89 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.next();
//        int size = sc.nextInt();
//        List<String> wordDict = new ArrayList<>();
//        for (int i = 0; i < size; i++) {
//            wordDict.add(sc.next());
//        }
        String fakeStr = "catsandog";
        List<String> wordDict = List.of("cats","dog","sand","and","cat");
        boolean b = wordBreak(fakeStr, wordDict);
        System.out.println("该单词是否能被字符串列表中的单词给替换:" + b);
    }

    /**
     * 单词拆分
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for(int i =1;i<=s.length();i++){
            for(int j =0;j<i;j++){
                if(dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
