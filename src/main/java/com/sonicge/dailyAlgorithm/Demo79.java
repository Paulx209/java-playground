package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【快速排序】
 *@Author: sonicge
 *@CreateTime: 2026-04-07
 */

public class Demo79 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] =sc.nextInt();
        }
        quickSort(nums,0,nums.length-1);
        for(int num:nums){
            System.out.println(num);
        }
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int index = partition(nums, left, right);
        quickSort(nums, left, index - 1);
        quickSort(nums, index+1, right);
    }


    public static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            //如果跳出循环，说明出现两个不符合的下标，进行交换
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = pivot;
        return i;
    }
}
