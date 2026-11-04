package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【最长重复子数组】
 *@Author: sonicge
 *@CreateTime: 2026-11-04
 */

public class Demo122 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len1 = sc.nextInt();
        int len2 = sc.nextInt();
        int[] arr1 = new int[len1];
        int[] arr2 = new int [len2];
        for(int i= 0;i<len1;i++){
            arr1[i] = sc.nextInt();
        }
        for(int i = 0;i<len2;i++){
            arr2[i] = sc.nextInt();
        }
        System.out.println("最长重复子数组的长度为：" + findLength(arr1,arr2));
    }
    public static int findLength(int[] nums1, int[] nums2) {
        //寻找最长连续子数组 -> 连续！
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1+1][len2+1];
        int max = 0;
        for(int i = 0;i<len1;i++){
            for(int j = 0;j<len2;j++){
                if(nums1[i] == nums2[j]){
                    dp[i+1][j+1] = dp[i][j]+1;
                    max = Math.max(max,dp[i+1][j+1]);
                }else{
                    dp[i+1][j+1] = 0;
                }
            }
        }
        return max;
    }
}
