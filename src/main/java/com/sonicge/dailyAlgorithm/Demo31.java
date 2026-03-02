package com.sonicge.dailyAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 【同构字符串】
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 输入：s = "egg", t = "add"
 * 输出：true
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-02
 */

public class Demo31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();

        boolean isomorphic = isIsomorphic(str1, str2);
        System.out.println("两个字符串是否为同构字符串: " + isomorphic);
    }

    /**
     * 同构字符串
     *
     * @param s 字符串1
     * @param t 字符串2
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        int lenA = s.length();
        int lenB = t.length();
        if (lenA != lenB) return false;

        Map<Character, Character> hashMap1 = new HashMap<>();
        Map<Character, Character> hashMap2 = new HashMap<>();

        for (int i = 0; i < lenA; i++) {
            char chA = s.charAt(i);
            char chB = t.charAt(i);
            if ((hashMap1.containsKey(chA) && hashMap1.get(chA) != chB) || (hashMap2.containsKey(chB) && hashMap2.get(chB) != chA)) {
                return false;
            }
            hashMap1.put(chA, chB);
            hashMap2.put(chB, chA);
        }
        return true;
    }

}
