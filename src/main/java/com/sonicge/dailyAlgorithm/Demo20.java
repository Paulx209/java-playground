package com.sonicge.dailyAlgorithm;


import java.util.Scanner;

/**
 * 【 两数之和 II - 输入有序数组 】
 * 给定一个数组，然后找出数组中满足相加之和为target的两个数，返回一个数组！
 * <p>
 *
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-22
 */

public class Demo20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        int[] res = twoSum(nums, target);
        System.out.println("返回的结果为:");
        for(int num : res){
            System.out.println(num);
        }

    }
    public  static int[] twoSum(int[] nums,int target){
        int left = 0;
        int right =nums.length-1;
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum < target){
                left++;
            }else if(sum > target){
                right--;
            }else{
                return new int[]{left+1,right+1};
            }
        }
        return new int[]{};
    }
}
