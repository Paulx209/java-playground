package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【全排列】
 *@Author: sonicge
 *@CreateTime: 2026-03-31
 */

public class Demo64 {
    static List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        boolean[] flags = new boolean[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        backTracking(nums, new ArrayList<>(), flags);
    }

    public static void backTracking(int[] nums, ArrayList<Integer> list, boolean[] flags) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!flags[i]) {
                list.add(nums[i]);
                flags[i] = true;
                backTracking(nums, list, flags);
                flags[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

}
