package com.sonicge.dailyAlgorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【最长的有效括号】
 *@Author: sonicge
 *@CreateTime: 2026-10-22
 */

public class Demo115 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int res = longestValidParentheses(str);
        System.out.println("最长有效括号的数量为:" + res);
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        int res = 0;
        for(int i = 0;i<s.length();i++){
            char ch = s.charAt(i);
            //左括号 入栈  存下标
            if(ch == '('){
                stack.push(i);
            }else{
                //右括号 stack不为空
                if(!stack.isEmpty()){
                    int index = stack.pop();
                    //此时为空 说明已经没有左括号 可以直接 i-start+1 更新
                    if(stack.isEmpty()){
                        res = Math.max(res,i-start+1);
                    }else{
                        //此时不为空 说明还有左括号 通过i-index+1 更新
                        res = Math.max(res,i-index+1);
                    }
                }else{ //右括号 stack为空 更新start边界
                    start = i+1;
                }
            }
        }
        return res;
    }

}
