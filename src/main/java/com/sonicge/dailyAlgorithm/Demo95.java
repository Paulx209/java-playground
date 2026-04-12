package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【最长回文子串】
 * 给你一个字符串 s，找到 s 中最长的 回文 子串。
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo95 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String response = longestPalindrome(str);
        System.out.println("最长回文子串为:" + response);
    }
    public static String longestPalindrome(String s) {
        String res="";
        int size = s.length();
        for(int i=0;i<size;i++){
            //奇数串
            String res1 = centerToSides(s, i, i);
            //偶数串 aa[bb]aa
            String res2 = centerToSides(s, i, i+1);
            res = res1.length() > res.length() ? res1 : res;
            res = res2.length() > res.length() ? res2 : res;
        }
        return res;
    }
    public static String centerToSides(String s,int left,int right){
        /**
         * 从中心向外扩展 如果不符合回文串的话 直接拒绝掉
         */
        while(left >=0 && right<s.length()){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else{
                break;
            }
        }
        //包左不包右 所以左边要+1 右边不用动
        return s.substring(left+1,right);
    }
}
