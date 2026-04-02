package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【寻找旋转排序数组中的最小值】
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。

 *@Author: sonicge
 *@CreateTime: 2026-04-02
 */

public class Demo70 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size =sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] = sc.nextInt();
        }
        int res = findMin(nums);
        System.out.println("旋转排序数组中的最小值为:" + res);
    }
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        while(left < right){
            int mid = left + (right - left) /2;
            if(nums[mid]< nums[right]){
                right = mid;
            }else{
                left = mid +1;
            }
        }
        return nums[left];
    }
}
