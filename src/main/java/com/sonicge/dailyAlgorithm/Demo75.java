package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【相交链表】 练习一下输入输出
 *  给定两个链表 有交点的话 给出交点；没有交点的话 返回null
 *@Author: sonicge
 *@CreateTime: 2026-04-07
 */

public class Demo75 {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(ListNode next) {
            this.next = next;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode[] initListNode() {
        Scanner sc = new Scanner(System.in);
        ListNode node1 = new ListNode(sc.nextInt());
        ListNode node2 = new ListNode(sc.nextInt());
        ListNode node3 = new ListNode(sc.nextInt());
        ListNode node4 = new ListNode(sc.nextInt());
        ListNode node5 = new ListNode(sc.nextInt());
        ListNode node6 = new ListNode(sc.nextInt());
        ListNode node7 = new ListNode(sc.nextInt());
        ListNode node8 = new ListNode(sc.nextInt());
        node1.next = node2;
        node2.next = node6;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        return new ListNode[]{node1,node3};
    }

    /**
     * 返回相交的节点
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA,ListNode headB){
        ListNode cur1 = headA;
        ListNode cur2 = headB;
        while(cur1 != cur2){
            cur1 = cur1 == null ? headB : cur1.next;
            cur2 = cur2 == null ? headA : cur2.next;
        }
        return cur1;
    }

    public static void main(String[] args) {
        ListNode[] listNodes = initListNode();
        ListNode res = getIntersectionNode(listNodes[0],listNodes[1]);
        System.out.println(res.val);
    }
}
