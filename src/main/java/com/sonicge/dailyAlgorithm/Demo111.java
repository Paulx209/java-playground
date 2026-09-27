package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 *@Author: sonicge
 *@CreateTime: 2026-09-26
 */

public class Demo111 {
    public static void main(String[] args) {
        //1.初始化
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] coins = new int[count];
        for(int i = 0;i<count;i++){
            coins[i] = sc.nextInt();
        }
        int money = sc.nextInt();

        //2.求money最少被几枚硬币可以替换掉
        System.out.println("money:" + money + "，可以被" + coinChange(coins,money) + "枚硬币替换掉");
    }
    public static  int coinChange(int[] coins, int money){
        int[] dp = new int[money+1];
        Arrays.fill(dp,money+1); //假设coins只有面值为1的硬币 那凑齐money 也需要 money个，这里设置成money +1，主要用来判断最后无法组成的情况
        dp[0] = 0; // money = 0 不需要硬币
        for(int i = 1; i<=money;i++){
            for(int j = 0;j<coins.length;j++){
                if(coins[j] > i){
                    continue;
                }
                //dp[i]表示的是 凑齐 i 最少需要的硬币数  当coins[j]越大的话 我们的dp可以越小
                dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
            }
        }
        return dp[money] == money+1 ?  -1  : dp[money];
     }
}
