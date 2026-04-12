package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【最小覆盖子串】
 *  滑动窗口
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo100 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        String response = minWindow(str1, str2);
        System.out.println("最小覆盖子串为:" + response);
    }

    public static String minWindow(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();

        int[] arr = new int[128]; //阿斯克码表的长度为128
        //1.欠债
        int count =0;
        for(char ch : ch2){
            arr[ch-'A']++;
            count++;
        }

        //2.还债
        int left = 0;
        int end = s.length();
        int start = -1;

        for(int right=0;right<s.length();right++){
            //i进行向右边扩张
            char ch =ch1[right];
            if (--arr[ch - 'A'] >= 0) {
                count--;
            }
            //滑动窗口形成 移动left缩小目标
            while(count ==0 ){
                char leftCh = ch1[left];
                if(++arr[leftCh-'A']>0){
                    if(right-left < end - start){
                        end = right;
                        start = left;
                    }
                    count++;
                }
                left++;
            }
        }
        return start  == -1 ? "" : s.substring(start,end+1);
    }
}
