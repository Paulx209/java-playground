package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【编辑距离】
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo97 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        int minCount = minDistance(str1, str2);
        System.out.println("最小编辑次数为:" + minCount);
    }

    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        //dp[i][j]表示什么意思？ 下标为i-1 , j-i的字符要相等需要的最小编辑次数？
        int[][] dp = new int[len1 + 1][len2 + 1];
        //初始化dp
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //fill dp
        for (int i = 1; i <= len1; i++) {
            char ch1= word1.charAt(i-1);
            for (int j = 1; j <= len2; j++) {
                if(ch1 == word2.charAt(j-1)){
                    //说明这两个字符不需要操作 状态只依赖于左上角
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //说明这两个字符需要操作：三种操作依赖的上一步操作不同，如果是新增的话，如果是删除的话，如果是替换的话。
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1]+1,dp[i-1][j]+1),dp[i][j-1]+1);
                }
            }
        }
        return dp[len1][len2];
    }
}
