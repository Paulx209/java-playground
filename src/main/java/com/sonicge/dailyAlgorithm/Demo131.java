package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *@Author: sonicge
 *@CreateTime: 2026-11-25
 */

public class Demo131 {
    static List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i = 0;i<size;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        permuteUnique(arr);
        for(List<Integer> part : res){
            for(Integer num :part){
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }
    public static void permuteUnique(int[] nums){
        int size = nums.length;
        boolean[] flags = new boolean[size];
        backTracking(nums,flags,new ArrayList<>());
    }
    public static void backTracking(int[] nums,boolean[] flags,List<Integer> part){
        if(part.size() == nums.length){
            res.add(new ArrayList<>(part));
            return ;
        }
        for(int i = 0;i<nums.length;i++){
            // 避免dfs时 重复处理一个元素 因为每次都从0开始
            if(flags[i]){
                continue;
            }
            // 如果水平方向上 for循环 前一个元素和当前元素的值一样 并且前一个元素已经处理过时  就直接跳过
            if(i > 0 && nums[i] == nums[i-1] && !flags[i-1]){
                continue;
            }
            part.add(nums[i]);
            flags[i] = true;
            backTracking(nums,flags,part);
            flags[i] = false;
            part.remove(part.size()-1);
        }
    }
}
