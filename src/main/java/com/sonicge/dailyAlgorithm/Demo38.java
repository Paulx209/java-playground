package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 【用最少数量的箭引爆气球】
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。
 * 在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 *
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-05
 */

public class Demo38 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[][] arr = new int[size][2];
        for (int i = 0; i < size; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int minArrowShots = findMinArrowShots(arr);
        System.out.println("射箭的最小数量为:" + minArrowShots);

    }

    /**
     * 用最少的数量的箭引爆气球
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        int res = 0;
        //按照start进行排序
        Arrays.sort(points, (a, b) -> {
            return Integer.compare(a[0],b[0]);
        });
        int[] pre = points[0];
        //开始遍历
        for (int i = 1; i < points.length; i++) {
            if(pre[1] >=points[i][0]){
                //这样说明要更新一下pre的end了
                pre[1] = Math.min(pre[1],points[i][1]);
            }else{
                res++;
                pre=points[i];
            }
        }
        res++;
        return res;
    }
}
