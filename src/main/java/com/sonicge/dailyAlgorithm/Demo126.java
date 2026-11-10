package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 【组合总和 II】 排序 + 去重 dfs的时候在同一层做去重判断
 *@Author: sonicge
 *@CreateTime: 2026-11-10
 */

public class Demo126 {
    static List<List<Integer>> res = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for(int i = 0;i<arr.length;i++){
            arr[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        combinationSum2(arr,target);
        for(List<Integer> list :res){
            for(Integer val : list){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
    static List<List<Integer>> combinationSum2(int[] candidates,int target){
        Arrays.sort(candidates);
        dfs(candidates,target,0,new ArrayList<>());
        return res;
    }
    public static void dfs(int[] candidates,int target,int start,List<Integer> part){
        if(target == 0){
            res.add(new ArrayList<>(part));
            return;
        }
        if(start >= candidates.length || target < 0){
            return;
        }
        //要做去重处理
        for(int i = start;i<candidates.length;i++){
            if(i>start && candidates[i] == candidates[i-1]){
                continue;
            }
            part.add(candidates[i]);
            dfs(candidates,target-candidates[i],i+1,part);
            part.remove(part.size()-1);
        }
    }
}
