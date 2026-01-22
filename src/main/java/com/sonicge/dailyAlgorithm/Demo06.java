package com.sonicge.dailyAlgorithm;

import java.io.FilterOutputStream;
import java.util.Scanner;

/**
 * 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-22
 */
public class Demo06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println("是否能够到达最后一个格子："+reachRemoteIndex(nums));

    }

    private static boolean reachRemoteIndex(int[] nums) {
        int size = nums.length;
        if (size == 1) return true;
        int remoteIndex = nums[0];
        for (int i = 0; i < size; i++) {
            if (remoteIndex >= size - 1) {
                return true;
            }
            if(remoteIndex >= i){
                remoteIndex = Math.max(remoteIndex,i+nums[i]);
            }
        }
        return false;
    }

}
