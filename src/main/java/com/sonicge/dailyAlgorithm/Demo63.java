package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【组合】
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *@Author: sonicge
 *@CreateTime: 2026-03-31
 */

public class Demo63 {
    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        backTracking(k, n, 1, new ArrayList<>());

    }

    public static void backTracking(int k, int n, int index, List<Integer> part) {
        if (part.size() == k) {
            res.add(new ArrayList<>(part));
            return;
        }
        for (int i = index; i <= n; i++) {
            part.add(i);
            backTracking(k, n, i + 1, part);
            part.remove(part.size() - 1);
        }
    }
}
