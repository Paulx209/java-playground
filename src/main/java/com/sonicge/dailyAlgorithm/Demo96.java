package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【最长公共子序列 多维dp】
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo96 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        int length = longestCommonSubsequence(str1, str2);
        System.out.println("最长公共子序列为:" + length);
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1]; //dp[i][j]:text1中i下标之前 和 text2中j下标之前 他们的最长公共子序列的长度
        for (int i = 1; i <= len1; i++) {
            char ch1 = text1.charAt(i-1);
            for (int j = 1; j <= len2; j++) {
                if(ch1 == text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
