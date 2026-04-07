package com.sonicge.dailyAlgorithm;

import java.util.Currency;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 【和为k的子数组】
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列
 *
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *@Author: sonicge
 *@CreateTime: 2026-04-07
 */

public class Demo74 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i =0;i<size;i++){
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int res = subarraySum(arr,k);
        System.out.println("和为K的子数组有：" + res);

    }

    /**
     * 使用前缀和 + 哈希表的思路
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums,int k){
        int preSum=0;
        int count=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(preSum,1);
        for(int num:nums){
            preSum+=num;
            if(map.containsKey(preSum-k)){
                count += map.get(preSum-k);
            }
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return count;
    }
}
