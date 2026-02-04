package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-04
 */
public class Demo12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] ratings = new int[size];
        for (int i = 0; i < size; i++) {
            ratings[i] = sc.nextInt();
        }
        System.out.println("累计接的雨水有多少个:" + trap(ratings));
    }

    public static int trap(int[] waters){
        int size=waters.length;
        int[] leftMaxHeight = new int[size];
        int[] rightMaxHeight = new int[size];
        //第一步：计算每一个index下标对应的格子左右两边的最大值
        int max=0;
        for(int i =1;i<size;i++){
            max=Math.max(max,waters[i-1]);
            leftMaxHeight[i] = max;
        }
        max=0;
        for(int i=size-2;i>=0;i--){
            max=Math.max(max,waters[i+1]);
            rightMaxHeight[i] = max;
        }
        //第二步：计算雨水 大致的逻辑就是：获取该节点对应的左右两边的最大值，然后取一个小的，和当前的节点比较，如果当前节点较小的话，说明可以接上；否则雨水就漏了。
        int result = 0;
        for(int i =1;i<size-1;i++){
            int currentWater = Math.max(leftMaxHeight[i],rightMaxHeight[i]);
            result = result +  currentWater - waters[i] > 0 ? currentWater - waters[i] : 0;
        }
        return result;
    }
}
