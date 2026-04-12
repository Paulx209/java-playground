package com.sonicge.dailyAlgorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 【滑动窗口最大值】
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-12
 */

public class Demo99 {
    static class MyDeque {
        Deque<Integer> queue = new ArrayDeque<>();

        void poll(int val) {
            if (!queue.isEmpty() && val == queue.peek()) {
                queue.poll();
            }
        }

        void add(int val) {
            while (!queue.isEmpty() && val > queue.getLast()) {
                queue.removeLast();
            }
            //只有前面有比我下的 全部删除 ，因此val的前面一定是比它大的或者他就是第一个
            queue.add(val);
        }

        int peek() {
            return queue.peek();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        int[] res = maxSlidingWindow(nums, k);
        for(int num:res){
            System.out.println(num);
        }
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int size = nums.length;
        int[] res = new int[size - k +1];
        MyDeque myDeque = new MyDeque();
        for(int i =0;i<k;i++){
            myDeque.add(nums[i]);
        }
        res[0] =myDeque.peek();
        for(int i=k;i<size;i++){
            myDeque.poll(nums[i-k]);
            myDeque.add(nums[i]);
            res[i-k+1] = myDeque.peek();
        }
        return res;
    }
}
