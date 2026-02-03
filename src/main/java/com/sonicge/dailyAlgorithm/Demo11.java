package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子中，评分更高的那个会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 *
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-03
 */
public class Demo11 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] ratings = new int[size];
        for (int i = 0; i < size; i++) {
            ratings[i] = sc.nextInt();
        }
        System.out.println("最少需要糖果的个数为:" + candy(ratings));
    }
    public static int candy(int[] ratings){
        int size = ratings.length;
        int[] candies = new int[size];
        candies[0] = 1;
        //由于是相邻的两个孩子，所以左边孩子和右边孩子都需要满足这个条件，因此先遍历左边，然后再遍历右边。
        //先遍历左边
        for(int i=1;i<size;i++){
            if(ratings[i] > ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }else{
                candies[i] = 1;
            }
        }
        //遍历右边
        for(int i=size -2 ; i>=0 ; i--){
            if(ratings[i] > ratings[i+1]){
                candies[i]= Math.max(candies[i],candies[i+1]+1);
            }
        }
        int res=0;
        for(int candy:candies){
            res+=candy;
        }
        return res;
    }
}
