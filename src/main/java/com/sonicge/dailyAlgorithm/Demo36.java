package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【汇总区间】
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 * 区间 [a,b] 是从 a 到 b（包含）的所有整数的集合。
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-04
 */

public class Demo36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        List<String> res = summaryRanges(arr);
        for (String str : res) {
            System.out.println(str);
        }
    }

    /**
     * 汇总区间
     *
     * @param nums
     * @return
     */
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        int start = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i-1]+1){
                //如果链条断掉之后，就出现问题了
                if(nums[i-1] == start){
                    //只有一个元素
                    list.add(String.valueOf(start));
                }else{
                    //有多个元素
                    list.add(start + "->" + nums[i-1]);
                }
                start = nums[i];
            }
        }
        //最后一轮连续的话，可能没有处理
        if(start == nums[nums.length -1]){
            list.add(String.valueOf(start));
        }else{
            list.add(start + "->" + nums[nums.length -1]);
        }
        return list;
    }
}
