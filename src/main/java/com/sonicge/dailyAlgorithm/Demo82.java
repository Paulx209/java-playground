package com.sonicge.dailyAlgorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 【字符串解码】
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-10
 */

public class Demo82 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        while(true){
            String str1 = sc.next();
            if(str1.equals("haha")){
                break;
            }
            String response = decodeString(str1);
            System.out.println(response);
        }
        System.out.println("退出");
    }
    public static  String decodeString(String s){
        Stack<StringBuilder> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        StringBuilder currentSb = new StringBuilder();
        Integer num = 0;
        for(char ch : s.toCharArray()){
            if(ch>='0' && ch<='9'){
                num = num*10 + (ch-'0');
            }else if(ch == '['){
                strStack.push(currentSb);
                numStack.push(num);
                num = 0;
                currentSb = new StringBuilder();
            }else if(ch == ']'){
                int size = numStack.pop();
                StringBuilder sb = strStack.pop();
                for(int i =0;i<size;i++){
                    sb.append(currentSb);
                }
                currentSb  = sb;
            }else{
                currentSb.append(ch);
            }
        }
        return currentSb.toString();
    }
}

