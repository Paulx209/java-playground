package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【最长公共子序列二】
 * 在最长公共子序列的基础上，需要回溯拼接出最长的子序列字符串。
 *@Author: sonicge
 *@CreateTime: 2026-11-24
 */

public class Demo128 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        String lcs = LCS(str1, str2);
        System.out.println("最长公共子序列为：" + lcs);
    }

    public static String LCS(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        // 开始回溯拼接字符串
        StringBuilder sb = new StringBuilder();
        int i = len1;
        int j = len2;
        while (i > 0 && j > 0) {
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                sb.append(s1.charAt(i-1));
                i--;
                j--;
            }else{
                //如果不相等的话 朝dp值大的那个方向走
                if(dp[i-1][j] > dp[i][j-1]){
                    i--;
                }else{
                    j--;
                }
            }
        }
        if(sb.isEmpty()){
            return "-1";
        }
        return sb.reverse().toString();
    }
}
