package com.sonicge.dailyAlgorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 【逆波兰表达式求值】
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-09
 */

public class Demo43 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = sc.next();
        }
        int res = evalRPN(strs);
        System.out.println("逆波兰表达式的值为: " + res);
    }

    /**
     * 逆波兰表达式求值
     *
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        Deque<Integer> numStack = new ArrayDeque<>();

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                //取出来最近的两个值
                Integer num2 = numStack.poll();
                Integer num1 = numStack.poll();
                if(token.equals("+")){
                    numStack.push(num1+num2);
                }else if(token.equals("-")){
                    numStack.push(num1-num2);
                }else if(token.equals("*")){
                    numStack.push(num1 * num2);
                }else{
                    numStack.push(num1 / num2);
                }
            }else{
                int num = Integer.parseInt(token);
                numStack.push(num);
            }
        }
        return numStack.peek();
    }
}
