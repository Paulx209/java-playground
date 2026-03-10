package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【环形链表】
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-10
 */

public class Demo44 {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 链表的初始化
     */
    public void initListNode() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        ListNode[] nodes = new ListNode[size];
        for (int i = 0; i < size; i++) {
            int num = sc.nextInt();
            ListNode listNode = new ListNode(num);
            nodes[i] = listNode;
        }
        int pos = sc.nextInt();
        for (int i = 0; i < size - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[size - 1].next = nodes[pos];

        boolean b = hasCycle(nodes[0]);
        System.out.println("成环了吗:" + b);
    }


    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        //快慢指针方法
        ListNode fast = head;
        ListNode slow = head;
        while (slow.next != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)return true;
        }
        return false;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        Demo44 test = new Demo44();
        test.initListNode();
    }
}
