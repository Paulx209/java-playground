package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *【三角形最小路径和 —— 从下往上dp】
 *@Author: sonicge
 *@CreateTime: 2026-09-27
 */

public class Demo112 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        //初始化
        List<List<Integer>> list = new ArrayList<>();
        for(int i =0;i<size;i++){
            List<Integer> part = new ArrayList<>();
            for(int j=0;j<=i;j++){
                part.add(sc.nextInt());
            }
            list.add(part);
        }
        System.out.println("三角形最小路径和为:" + minimumTotal(list));
    }
    public static int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        if(size == 1){
            return triangle.get(0).get(0);
        }
        //按照下层结点来看 所有的arr[i][0] 只能来自上层结点
        int[][] dp = new int[size][size];
        //初始化
        for(int i = 0;i<size;i++){
            dp[size-1][i] = triangle.get(size-1).get(i);
        }
        //从上往下开始dp
        //开始dp
        for(int i = size-2;i>=0;i--){
            for(int j = 0;j< triangle.get(i).size();j++){
                dp[i][j] = Math.min(dp[i+1][j],dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
