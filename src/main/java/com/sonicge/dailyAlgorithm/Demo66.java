package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【括号生成】
 *@Author: sonicge
 *@CreateTime: 2026-03-31
 */

public class Demo66 {
    static StringBuilder sb = new StringBuilder();
    static List<String> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        backTracking(n, 0, 0);

        //遍历结果
        for (String part : res) {
            System.out.println(part);
        }
    }

    public static void backTracking(int n, int left, int right) {
        if (sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        //优先判断左括号
        if (left < n) {
            sb.append("(");
            backTracking(n, left + 1, right);
            sb.deleteCharAt(sb.length() - 1);
        }
        //然后判断右括号
        if (right < left) {
            sb.append(")");
            backTracking(n, left, right + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
