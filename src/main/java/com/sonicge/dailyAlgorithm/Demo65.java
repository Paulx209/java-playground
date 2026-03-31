package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【组合总和】
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *@Author: sonicge
 *@CreateTime: 2026-03-31
 */

public class Demo65 {
    public static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] candidates = new int[size];
        for (int i = 0; i < size; i++) {
            candidates[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        backTracking(candidates, target, 0, 0, new ArrayList<>());
    }

    public static void backTracking(int[] candidates, int target, int current, int start, List<Integer> part) {
        if (current > target) {
            return;
        }
        if (current == target) {
            res.add(new ArrayList<>(part));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            part.add(candidates[i]);
            if (current > target) return;
            backTracking(candidates, target, current + candidates[i], i , part);
            part.remove(part.size() - 1);
        }
    }
}
