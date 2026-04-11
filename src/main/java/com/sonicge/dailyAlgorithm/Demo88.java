package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【最长递增子序列】
 *  给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。子序列 是由数组派生而来的序列，
 *  删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *  输入：nums = [10,9,2,5,3,7,101,18]
 *  输出：4
 *  解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo88 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i] = sc.nextInt();
        }
        int i = lengthOfLIS(arr);
        System.out.println("最长递增子序列为：" + i);
    }

    /**
     * 最长递增子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        //定义dp数组，长度和nums数组的长度相同即可 dp[i]表示当前下标为止 递增子序列的一个最大长度
        int[] dp = new int[nums.length];
        //初始化dp中的值为1
        Arrays.fill(dp, 1);
        int res = 1;
        //开始dp遍历
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    //更新dp
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    res =Math.max(res,dp[i]);
                }
            }
        }
        return res;
    }
}
