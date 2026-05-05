package com.sonicge.dailyAlgorithm;

import java.util.Stack;

/**
 * 【最小栈】
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 *      void push(int val) 将元素val推入堆栈。
 *      void pop() 删除堆栈顶部的元素。
 *      int top() 获取堆栈顶部的元素。
 *      int getMin() 获取堆栈中的最小元素。
 *@Author: sonicge
 *@CreateTime: 2026-04-25
 */

public class Demo103 {
    static Stack<Integer> numStack = new Stack<>();
    static Stack<Integer> minStack = new Stack<>();

    public static void push(int val) {
        numStack.push(val);
        minStack.push(Math.min(minStack.peek(),val));
    }

    public static int poll() {
        Integer pop = numStack.pop();
        minStack.pop();
        return pop;
    }

    public static int top() {
        return numStack.peek();
    }

    public static int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {

    }
}
