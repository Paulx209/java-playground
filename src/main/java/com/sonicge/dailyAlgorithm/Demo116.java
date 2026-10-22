package com.sonicge.dailyAlgorithm;

import cn.hutool.core.lang.Assert;

import java.util.Stack;

/**
 * 【用栈实现队列】
 *@Author: sonicge
 *@CreateTime: 2026-10-22
 */

public class Demo116 {
    static class MyQueue{
        Stack<Integer> inStack;
        Stack<Integer> outStack;
        public MyQueue(){
            inStack = new Stack<>();
            outStack = new Stack<>();
        }
        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            if(outStack.isEmpty()){
                while(!inStack.isEmpty()){
                    int val = inStack.pop();
                    outStack.push(val);
                }
            }
            return outStack.pop();
        }

        public int peek() {
            if(outStack.isEmpty()){
                while(!inStack.isEmpty()){
                    int val = inStack.pop();
                    outStack.push(val);
                }
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }
    }
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        Assert.isTrue(queue.peek() == 1,"你错了哈哈");
        Assert.isTrue(queue.pop() == 1,"你错了哈哈");
        Assert.isTrue(queue.peek() == 2,"你错了哈哈");
    }
}
