package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【环形子数组的最大和】
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * 【最大子数组和】的进阶版
 * @Author: sonicge
 * @CreateTime: 2026-09-22
 */

public class Demo110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("环形子数组的最大和：" + maxSubarraySumCircular(arr));
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int res1 = Integer.MIN_VALUE;
        int res2 = Integer.MAX_VALUE;

        int pre1 = 0, pre2 = 0, total = 0;

        //分别求最大子数组和 、 最小子数组和 、 所有元素和
        for(int num : nums){
            //最大子数组和
            if(pre1 < 0){
                pre1 = num;
            }else{
                pre1 += num;
            }
            res1 = Math.max(res1,pre1);

            //最小子数组和
            if(pre2 > 0){
                pre2 = num;
            }else{
                pre2 += num;
            }
            res2 = Math.min(res2,pre2);

            //所有元素和
            total += num;
        }
        //环形子数组和 = 非环形子数组最大值 or 所有元素和 - 非环形子数组最小值
        //但是还有一种情况是 所有值都为负数时  所有元素和 - 非环形子数组最小值 == 0 这种情况要舍弃掉
        res2 = total-res2 == 0 ?Integer.MIN_VALUE : total-res2;
        return Math.max(res1,res2);
    }
}
