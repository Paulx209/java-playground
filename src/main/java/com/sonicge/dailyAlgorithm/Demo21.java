package com.sonicge.dailyAlgorithm;


import java.util.Scanner;

/**
 * 【 盛最多水的容器 】
 * 给定一个长度为 n 的整数数组 height，数组中每个元素 height[i] 代表第 i 条垂直于 x 轴的线段高度（线段两端点为 (i, 0) 和 (i, height[i])）。
 * 需从这 n 条线段中选出两条，使它们与 x 轴围成一个不能倾斜的容器，目标是找到能容纳最大水量的组合，并返回这个最大水量。
 * <p>
 *
 输入：[1,8,6,2,5,4,8,3,7]
 输出：49
 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-22
 */

public class Demo21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for(int i=0;i<size;i++){
            nums[i] = sc.nextInt();
        }

        int maxArea = maxArea(nums);
        System.out.println("盛水最多的容器的体积为：" + maxArea);
    }
    public static  int maxArea(int[] height){
        int maxRes = 0;
        int left = 0;
        int right = height.length-1;
        while(left < right){
            int currentHeight = Math.min(height[left],height[right]);
            maxRes = Math.max(maxRes,currentHeight * (right-left));
            //移动哪一边？
            if(currentHeight ==  height[left]){
                left++;
            }else{
                right--;
            }
        }
        return maxRes;
    }
}
