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

    /**
     * 主要是 minStack 的入栈：如果最小栈为空，直接 push；如果新来的元素 <= 最小值的话 也要入栈
     */
    public static void push_v2(int val){
        numStack.push(val);
        if(minStack.isEmpty() || val <= minStack.peek()){
            numStack.push(val);
        }
    }

    /**
     * push的逻辑不同，poll的逻辑也会变化，这里只有在淘汰最小值的时候才会pop
     */
    public static void poll_v2(){
        int val = numStack.pop();
        if(val == minStack.peek()){
            minStack.pop();
        }
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
