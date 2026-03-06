package com.sonicge.dailyAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 【插入区间】
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，
 * 其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。
 * <p>
 * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，
 * 且区间之间不重叠（如果有必要的话，可以合并区间）。
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-06
 */

public class Demo39 {
    public static void main(String[] args) {
        //初始化
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[][] arr = new int[size][2];
        for (int i = 0; i < size; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int[] newInterval = new int[2];
        newInterval[0] = sc.nextInt();
        newInterval[1] = sc.nextInt();

        //插入区间
        int[][] ans = insert(arr, newInterval);
        for(int[] part :ans){
            System.out.println("start: "+part[0] + " end:"+part[1]);
        }

    }
    public static int[][] insert(int[][] intervals, int[] newInterval){
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int size = intervals.length;
        //第一步，先把所有插入区间左侧的interval都插入进去
        while(i<size && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        //第二步，开始合并merge
        while(i<size && intervals[i][0] <=newInterval[1]){
            newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        //从pre开始，开始合并
        while(i<size){
            res.add(intervals[i++]);
        }
        //转换为二维数组
        int[][] ans = new int[res.size()][2];
        int index=0;
        for(int[] part:res){
            ans[index] = part;
            index++;
        }
        return ans;
    }
}
