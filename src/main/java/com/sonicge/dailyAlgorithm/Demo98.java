package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【下一个排列】
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 三部曲
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo98 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] =sc.nextInt();
        }
        nextPermutation(nums);
        for(int num:nums){
            System.out.println(num);
        }
    }

    /**
     * 下一个排列
     * 1 4 2 5 3 第一步：找到拐点为2；第二步，拐点和最小的大于它的数交换，1 4 3 5 2 ; 第三步，交换之后后面的要按照从小到大排,1 4 3 2 5
     *  1 4 2 5 3 -> 1 4 3 2 5
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        // 1.找到拐点
        int index = -1;
        for(int i = nums.length-2;i>=0;i--){
            if(nums[i] < nums[i+1]){
                index = i;
                break;
            }
        }
        if(index == -1){
            reverse(nums,0,nums.length-1);
            return;
        }
        // 2.找到第一个大于拐点值的元素
        for(int i = nums.length-1;i>index;i--){
            if(nums[i] > nums[index]){
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        // 3. 拐点后面的所有元素进行reverse
        reverse(nums,index+1,nums.length-1);

    }
    public static void reverse(int[] nums,int left,int right){
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
