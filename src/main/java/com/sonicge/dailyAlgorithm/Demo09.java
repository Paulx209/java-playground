package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【除了自身以外数组的乘积】
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-26
 */
public class Demo09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        //使用上下三角法来解决
        int[] res = new int[size]; // 返回的结果数组 res[i] = 除了自身以外数组的乘积

        //先来左边正三角
        res[0] = 1;
        for (int i = 1; i < size; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        //再来右边倒三角
        int temp = 1;
        for (int i = size - 2; i >= 0; i--) {
            temp*=nums[i+1];
            res[i]*=temp;
        }
        //打印输出
        for(int num:res){
            System.out.println(num);
        }
    }
}
