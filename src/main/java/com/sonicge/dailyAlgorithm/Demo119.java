package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【最大数】
 *@Author: sonicge
 *@CreateTime: 2026-10-29
 */

public class Demo119 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println("最大值为:" + largestNumber(nums));
    }
    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i =0;i<nums.length;i++){
            strs[i] = String.valueOf(nums[i]);
        }
        //开始排序
        Arrays.sort(strs,(a,b) -> (b+a).compareTo(a+b));
        if("0".equals(strs[0])){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
        }
        return sb.toString();
    }
}
