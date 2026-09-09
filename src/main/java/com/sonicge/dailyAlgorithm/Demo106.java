package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【删除有序数组中的重复项 II】
 * @Author: sonicge
 * @CreateTime: 2026-09-09
 */

public class Demo106 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i= 0 ;i<size;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println("removeDuplicates函数处理");
        System.out.println(removeDuplicates(arr));
    }

    /**
     * 请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次
     * @param nums  array
     * @return 返回删除后数组的新长度
     */
    public static int removeDuplicates (int[] nums){
        int length = nums.length;
        if(length <= 2){
            return length;
        }
        //双指针
        int slow = 2;
        int fast = 2;
        while(fast < length){
            //不相等 说明fast下标的元素不冲突
            if(nums[fast] != nums[slow-2]){
                nums[slow] = nums[fast];
                slow++;
            }
            //相等没办法处理 fast++ ; 不相等的话也要fast++
            fast++;
        }
        //slow最后是多加了一个的 相当于表示元素个数了
        return slow;
    }
}
