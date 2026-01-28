package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【加油站】
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * 输出: 3
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 *
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-28
 */
public class Demo10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] gas = new int[size];
        for (int i = 0; i < size; i++) {
            gas[i] = sc.nextInt();
        }
        int[] cost =  new int[size];
        for (int i = 0; i < size; i++) {
            cost[i] = sc.nextInt();
        }
        int res = canCompleteCircuit(gas,cost);
        System.out.println("加油站的下标为:" + res);
    }
    public static int canCompleteCircuit(int[] gas, int[] cost){
        int sum = 0 ; //总的油量盈余
        int currentSum = 0; //当前startIndex的油量盈余
        int startIndex = 0; //起点

        for(int i=0;i<gas.length;i++){
            int remain =gas[i]-cost[i]; //计算出来剩余的
            sum += remain;
            currentSum +=remain;
            if(currentSum < 0){
                startIndex = i+1; // 这里直接更新为i+1 ； 为什么不更新为startIndex + 1呢？因为如果startIndex不行的话，startIndex+1更不行；只能到下一次轮
                currentSum = 0;
            }
        }
        return sum < 0 ? -1 : startIndex;
    }

}
