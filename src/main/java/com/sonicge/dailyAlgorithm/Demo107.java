package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【380. O(1) 时间插入、删除和获取随机元素】
 * @Author: sonicge
 * @CreateTime: 2026-09-10
 */

public class Demo107 {
    static class RandomizedSet{
        Random random;
        List<Integer> list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        public RandomizedSet(){
            random = new Random();
        }

        /**
         * 实现O(1)插入元素。如果元素val不存在的话，插入并且返回true；如果元素存在的话，直接返回false
         */
        boolean insert(int val){
            //O(1)
            if(map.containsKey(val)){
                return false;
            }
            list.add(val);
            map.put(val,list.size()-1);
            return true;
        }

        /**
         * 移除元素，如果元素val存在的话，移除并且返回true；如果不存在的话，直接返回false
         */
//        boolean remove(int val){
//            if(!map.containsKey(val)){
//                return false;
//            }
//            int removedIndex = map.get(val);
//            Integer value = list.getLast();
//            list.removeLast();
//            list.set(removedIndex,value);
//            map.remove(val);
//            map.put(value,removedIndex);
//            return true;
//        }
        int getRandom(){
            int index = random.nextInt(list.size());
            return list.get(index);
        }

    }
    public static void main(String[] args) {
        //
    }
}
