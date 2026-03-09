package com.sonicge.dailyAlgorithm;

import java.util.*;

/**
 * 【最小栈】
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * int getMin() 获取堆栈中的最小元素。
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-09
 */

public class Demo42 {
    public static Deque<Integer> stack;
    public static List<Integer> list;
    public Demo42(){
        stack = new ArrayDeque<>();
        list = new ArrayList<>();
    }
    public static  void push(int val) {
        list.add(val);
        Collections.sort(list);
        stack.push(val);
    }

    public static void pop() {
        Integer element = stack.pop();
        list.remove(element);
    }

    public static int top() {
        return stack.peek();
    }

    public static  int getMin() {
        return list.get(0);
    }

    public static void main(String[] args) {
        Demo42 demo42 = new Demo42();
    }
}
