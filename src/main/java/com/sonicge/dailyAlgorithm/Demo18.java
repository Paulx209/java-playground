package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 【验证回文串】
 *如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 * 字母和数字都属于字母数字字符。
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-17
 */

public class Demo18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        //1.对字符串进行处理（小写字符）
        String resStr = handleStr(str);
        //2.判断是否是回文串
        boolean hw = isHw(resStr);
        System.out.println("字符串是回文串：" + hw);

    }

    public static String handleStr(String str){
        String lowerCase = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch = lowerCase.charAt(i);
            if((ch>='A'&&ch<='Z')||(ch>='a'&&ch<='z')||(ch>='0'&&ch<='9'))sb.append(ch);
        }
        return sb.toString();
    }

    public static boolean isHw(String str){
        int left = 0;
        int right = str.length();
        while(left<right){
            char leftCh = str.charAt(left);
            char rightCh = str.charAt(right - 1);
            if(leftCh != rightCh)return false;
            left++;
            right--;
        }
        return true;
    }

}
