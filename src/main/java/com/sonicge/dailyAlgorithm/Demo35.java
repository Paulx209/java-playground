package com.sonicge.dailyAlgorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 【最长连续序列】
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-03
 */

public class Demo35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        int res = longestConsecutive(nums);
        System.out.println("最长连续序列为: "+ res);
    }

    public static int longestConsecutive(int[] nums) {
        //最长连续序列
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        //开始遍历
        int res  = 0;
        for(int num : hashSet){
            if(!hashSet.contains(num-1)){
                //说明该值是最小的
                int part = 1;
                while(hashSet.contains(num+part)){
                    part++;
                }
                res = Math.max(part,res);
            }
        }
        return res;
    }
}
