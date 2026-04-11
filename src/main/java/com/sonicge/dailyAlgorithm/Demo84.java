package com.sonicge.dailyAlgorithm;


import java.util.Scanner;
import java.util.Stack;

/**
 * 【柱状图中最大的矩形】
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo84 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        int maxArea = largestRectangleArea(nums);
        System.out.println("最大的面积为:" + maxArea);
    }

    /**
     * 柱状图中最大的矩形
     * @param heights
     */
    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        //1.将heights拆成 0 heights 0
        int[] new_heights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            new_heights[i] = heights[i - 1];
        }
        //2.使用一个单调递增栈来维护元素的单调性！
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < new_heights.length; i++) {
            while (!stack.isEmpty() && new_heights[i] < new_heights[stack.peek()]) {
                int height = new_heights[stack.pop()];
                int length = i - stack.peek() -1 ;
                res = Math.max(res,height*length);
            }
            stack.push(i);
        }

        return res;
    }
}
