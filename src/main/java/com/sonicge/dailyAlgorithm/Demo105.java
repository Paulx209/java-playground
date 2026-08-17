package com.sonicge.dailyAlgorithm;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *@Author: sonicge
 *@CreateTime: 2026-08-17
 */

public class Demo105 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i =0;i<size;i++){
            arr[i] = sc.nextInt();
        }

        quickSort(arr,0,size-1);
        printf(arr);

    }
    static void printf(int[] arr){
        for(int num : arr){
            System.out.println(num);
        }
    }

    /**
     * 三路快排
     */
    public static void quickSort(int[] arr,int left,int right){
        //1.递归终止条件
        if(left >= right)return;
        //2.随机选取pivotIndex 包左不包右
        int pivotIndex = ThreadLocalRandom.current().nextInt(left,right+1);
        int pivot = arr[pivotIndex];
        //3.定义变量 开始循环处理
        int less = left;
        int current = left;
        int greater = right;
        while(current <= greater){
            if(arr[current] < pivot){
                swap(arr,less,current);
                current++;
                less++;
            }else if(arr[current] == pivot){
                current++;
            }else if(arr[current] > pivot){
                swap(arr,current,greater);
                greater--;
            }
        }
        //4.递归处理 < > 区域
        quickSort(arr,left,less-1);
        quickSort(arr,greater+1,right);
    }

    static void swap(int[] arr,int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
