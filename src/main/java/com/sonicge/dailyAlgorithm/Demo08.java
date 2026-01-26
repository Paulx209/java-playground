package com.sonicge.dailyAlgorithm;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * <p>
 * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * <p>
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 *
 * @Author: sonicge
 * @CreateTime: 2026-01-26
 */
public class Demo08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = sc.nextInt();
        }
        int hIndex = hIndex(nums);
        System.out.println("hIndex指数为:"+hIndex);
    }

    /**
     * 控制变量法 首先计算 size -i : 有多少个数字大于citations[i] ; 然后判断citations[i] > hIndex ； 这样就可以计算出来  有hIndex篇论文的引用次数大于hIndex
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int size = citations.length;
        int hIndex = 0;
        for (int i = 0; i < size; i++) {
            int hIndexCandidate = size-i;
            if(citations[i] >= hIndexCandidate){
                System.out.println("当前的下标为:"+i + "当前的hIndex指数为:"+hIndex);
                hIndex = Math.max(hIndex,hIndexCandidate);
            }
        }
        return hIndex;
    }
}
