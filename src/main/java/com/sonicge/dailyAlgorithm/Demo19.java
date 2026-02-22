package com.sonicge.dailyAlgorithm;


import java.util.Scanner;

/**
 * 【判断子序列】
 * 给定字符串a,b。判断字符串a是否为字符串b的子序列。
 * 子序列判断的规则为；不要求字符为连续的，但是字符之间的相对位置不能改变！
 * <p>
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-22
 */

public class Demo19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();

        boolean subSequence = isSubSequence(a, b);
        System.out.println("字符串a" + (subSequence ? "是" : "不是") + "字符串b的子序列");

    }

    public static boolean isSubSequence(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        if (lenA > lenB) return false;
        //使用双指针挨个判断
        int leftA = 0;
        int leftB = 0;
        while (leftB < lenB) {
            if (leftA < lenA && a.charAt(leftA) == b.charAt(leftB)) {
                leftA++;
            }
            if (leftA == lenA) return true;
            leftB++;
        }
        return false;
    }
}
