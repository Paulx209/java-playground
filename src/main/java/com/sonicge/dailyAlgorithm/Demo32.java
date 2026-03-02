package com.sonicge.dailyAlgorithm;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 【单词规律】
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 * 输入: pattern = "abba", s = "dog cat cat dog"
 * 输出: true
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-02
 */

public class Demo32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();

        boolean b = wordPattern(str1, str2);
        System.out.println("是否符合单词规律:" + b);
    }
    public static  boolean wordPattern(String pattern, String s) {
        HashMap<Character,String> map = new HashMap<>();
        HashMap<String,Character> map2 = new HashMap<>();
        String[] arr = s.split(" ");
        int lenA = pattern.length();
        int lenB = arr.length;
        if (lenA != lenB)
            return false;

        for (int i = 0; i < lenA; i++) {
            char ch = pattern.charAt(i);
            String str = arr[i];
            if( (map.containsKey(ch) && !(map.get(ch).equals(str)) ) || (map2.containsKey(str) && map2.get(str) != ch)){
                return false;
            }
            map.put(ch,str);
            map2.put(str,ch);
        }

        return true;
    }
}
