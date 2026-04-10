package com.sonicge.dailyAlgorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【每日温度 -> 单调栈】
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-10
 */

public class Demo83 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] temperatures = new int[size];
        for(int i = 0;i<size;i++){
            temperatures[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for(int i=0;i<size;i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int index = stack.pop();
                res[index] = i - index;
            }
            stack.push(i);
        }
        //输出结果
        for(int num:res){
            System.out.println(num);
        }
    }
}
