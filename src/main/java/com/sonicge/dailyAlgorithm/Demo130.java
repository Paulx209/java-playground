package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【数组中出现次数超过一半的数字】
 *@Author: sonicge
 *@CreateTime: 2026-11-25
 */

public class Demo130 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i = 0;i<size;i++){
            arr[i] = sc.nextInt();
        }
        int res = MoreThanHalfNum_Solution(arr);
        System.out.println("出现半数以上的数字是:" + res);
    }
    // 思路：数组中保证有数字出现的次数超过一半，相等为正，不相等为反，所以就正反相抵，为0时更新candidate。最后的候选者一定是出现次数超过一半的数字
    public static int MoreThanHalfNum_Solution(int[] arr){
        int candidate = arr[0];
        int voteCount = 0;
        for(int num :arr){
            if(num == candidate){
                //++
                voteCount++;
            }else{
                voteCount--;
                if(voteCount == 0){
                    candidate = num;
                    voteCount++;
                }
            }
        }
        return candidate;
    }
}
