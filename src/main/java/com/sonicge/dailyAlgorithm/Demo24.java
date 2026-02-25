package com.sonicge.dailyAlgorithm;


import java.util.*;

/**
 * 【无重复字符的最长子串】
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
 *
 * @Author: sonicge
 * @CreateTime: 2026-02-25
 */

public class Demo24 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int length = lengthOfLongestSubstring(str);
        System.out.println("不重复的最长子串的长度为: " + length);
    }

    /**
     * 找到不重复的最长子串
     * @param
     * @return
     */
    public static int lengthOfLongestSubstring(String s){
        Map<Character,Integer> hashMap = new HashMap<>();
        Integer res=0;
        int left = 0;
        int right = 0;
        while(right < s.length()){
            char ch = s.charAt(right);
            //while循环可以确保我们的ch的数量为0
            while (hashMap.getOrDefault(ch,0) != 0){
                //更新hashMap 然后left++
                char leftCh = s.charAt(left);
                hashMap.put(leftCh,hashMap.get(leftCh)-1);
                left++;
            }
            hashMap.put(ch,1);
            res = Math.max(res,right-left+1);
            right++;
        }
        return res;
    }

}
