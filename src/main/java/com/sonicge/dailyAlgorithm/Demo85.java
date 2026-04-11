package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【划分字母区间】
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 *
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo85 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        List<Integer> res = partitionLabels(str);
        for(Integer num : res){
            System.out.println(num);
        }

    }
    public static List<Integer> partitionLabels(String s){
        List<Integer> res = new ArrayList<>();
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            map.put(ch,i);
        }
        //开始贪心
        int maxIndex = 0;
        int start = 0;
        for(int i =0;i<s.length();i++){
            char ch = s.charAt(i);
            maxIndex = Math.max(maxIndex,map.get(ch));
            //如果当前的i到达最远下标 就说明该范围中涉及到的字符 后面都不会再涉及到了
            if(maxIndex == i){
                res.add(i-start+1);
                start = i+1;
            }
        }
        return res;
    }
}
