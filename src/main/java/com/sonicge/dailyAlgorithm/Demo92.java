package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【排序链表】
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo92 {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode initListNode() {
        Scanner sc = new Scanner(System.in);
        ListNode node = new ListNode(0);
        int size = sc.nextInt();
        ListNode temp = node;
        while (size > 0) {
            temp.next = new ListNode(sc.nextInt());
            temp = temp.next;
            size--;
        }
        return node.next;
    }

    /**
     * 排序链表 二分排序
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        //递归终止条件
        if (head == null || head.next == null) return head;
        //1.快慢指针找到中间值
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;
        //2.然后分别递归进行排序
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (left != null || right != null) {
            int val1 = left != null ? left.val : Integer.MAX_VALUE;
            int val2 = right != null ? right.val : Integer.MAX_VALUE;
            if (val1 < val2) {
                cur.next = new ListNode(val1);
                cur = cur.next;
                left = left.next;
            } else {
                cur.next = new ListNode(val2);
                cur = cur.next;
                right = right.next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head = initListNode();
        ListNode node = sortList(head);
        while(node!=null){
            System.out.println(node.val);
            node = node.next;
        }
    }

}
