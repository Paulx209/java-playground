package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 【合并区间】
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-04
 */

public class Demo37 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }

        int[][] merge = merge(intervals);
        for(int[] arr :merge){
            System.out.println("start:"+arr[0] + " end:"+arr[1]);
        }
    }

    public static int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        //先将二维数组进行排序
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        //然后开始找
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if(pre[1]>=intervals[i][0]){
                pre[1]=Math.max(pre[1],intervals[i][1]);
            }else{
                res.add(pre);
                pre = intervals[i];
            }
        }
        res.add(pre);
        //将List集合中的int[] 收集起来
        int size = res.size();
        int[][] finalRes = new int[size][2];
        for(int i = 0 ; i<size;i++){
            finalRes[i] = res.get(i);
        }
        return finalRes;
    }
}
