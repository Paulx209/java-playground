package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【字母异位词分组】
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-03
 */

public class Demo34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        String[] strs = new String[size];
        for (int i = 0; i < size; i++) {
            strs[i] = sc.next();
        }

        List<List<String>> lists = groupAnagrams(strs);
        for(List<String> part : lists){
            for(String str :part){
                System.out.print(str + " ");
            }
            System.out.println(" ");
        }
    }

    /**
     * 字母异位词分组
     * @param strs
     * @return
     */
    public static  List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> hashMap = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(String str : strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            if (hashMap.containsKey(sortedStr)) {
                List<String> list = hashMap.get(sortedStr);
                list.add(str);
            }else{
                List<String> newList = new ArrayList<>();
                newList.add(str);
                hashMap.put(sortedStr,newList);
            }
        }
        Iterator<List<String>> iterator = hashMap.values().iterator();
        while(iterator.hasNext()){
            List<String> part = iterator.next();
            res.add(part);
        }
        return res;
    }
}
