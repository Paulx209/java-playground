package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为 4。
 */

public class Demo15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int res = lengthOfLastWord(str);
        System.out.println("最后一个单词的长度：" + res);
    }

    public static  int lengthOfLastWord(String s) {
        int size=s.length();
        int index=size-1;
        while(index >= 0 && s.charAt(index) == ' '){
            index--;
        }
        int res=0;
        if(index < 0)return res;
        while(index >= 0 && s.charAt(index) != ' '){
            res++;
            index--;
        }
        return res;
    }
}
