package com.sonicge.dailyAlgorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【有效的括号】
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-07
 */

public class Demo40 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        boolean valid = isValid(str);
        System.out.println("是否为有效的括号:"+valid);
    }
    public static boolean isValid(String s){
        char ch = s.charAt(0);
        if(ch == ')' || ch == '}' || ch==']')return false;
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char currentCh = s.charAt(i);
            if(currentCh =='(' || currentCh == '{' || currentCh=='['){
                stack.push(currentCh);
            }else{
                if(stack.isEmpty())return false;
                Character pop = stack.pop();
                if((pop == '(' && currentCh !=')') || (pop == '{' && currentCh!='}' || (pop == '[' && currentCh!=']'))){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
