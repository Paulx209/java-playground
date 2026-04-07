package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【反转链表 递归法】
 *@Author: sonicge
 *@CreateTime: 2026-04-07
 */

public class Demo76 {
    static class ListNode {
        int val;
        Demo76.ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(Demo76.ListNode next) {
            this.next = next;
        }

        public ListNode(int val, Demo76.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static Demo76.ListNode initListNode() {
        Scanner sc = new Scanner(System.in);
        Demo76.ListNode node1 = new Demo76.ListNode(sc.nextInt());
        Demo76.ListNode node2 = new Demo76.ListNode(sc.nextInt());
        Demo76.ListNode node3 = new Demo76.ListNode(sc.nextInt());
        Demo76.ListNode node4 = new Demo76.ListNode(sc.nextInt());
        Demo76.ListNode node5 = new Demo76.ListNode(sc.nextInt());
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */

    static ListNode res;
    public static  ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        dfs(head);
        return res;
    }
    public static ListNode dfs(ListNode head){
        if(head.next == null){
            //说明到达最后了
            res = head;
            return res;
        }
        ListNode preNode = dfs(head.next);
        head.next = null;
        preNode.next = head;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = initListNode();
        ListNode newHead = reverseList(listNode);
        while(newHead !=null){
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}
