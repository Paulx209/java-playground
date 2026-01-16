package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 删除有序数组中的重复项
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * 元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 *
 * 输出：5, nums = [0,1,2,3,4,_,_,_,_,_]
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-16
 */
public class Demo02 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for(int i=0;i<size;i++){
            arr[i]=scanner.nextInt();
        }
        //核心代码
        int slow = 0;
        int fast = 1;
        while(fast < size){
            if(arr[slow] != arr[fast]){
                slow ++ ;
                arr[slow] = arr[fast];
            }
            fast++;
        }

        //验证
        for(int num: arr){
            System.out.println(num);
        }

    }
}
