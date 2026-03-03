package com.sonicge.dailyAlgorithm;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 【开心数】
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。[替换为平方和]
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。 [如果可以成为1 说明是开心数]
 * 如果这个过程 结果为 1，那么这个数就是快乐数。 [如果无限循环的话，说明不是开心数]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-03
 */

public class Demo33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        boolean happy = isHappy(num);
        System.out.println("num是否为开心数:" + happy);
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        //如果n==1的话，说明我们找到了开心数；如果set集合中包含了开心数的话，说明遇到循环了
        while (n != 1 && !set.contains(happyNum(n))) {
            int num = happyNum(n);
            set.add(num);
            n = num;
        }
        return n == 1;
    }

    public static int happyNum(int num) {
        //返回各个数的平方和
        int res = 0;
        while (num != 0) {
            res += Math.pow(num % 10, 2);
            num /= 10;
        }
        return res;
    }
}
