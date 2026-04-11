package com.sonicge.dailyAlgorithm;



/**
 * 【合并k个升序链表】
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 *@Author: sonicge
 *@CreateTime: 2026-04-11
 */

public class Demo93 {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        //todo list的初始化
        ListNode[] lists = new ListNode[3];
        ListNode res = mergeKLists(lists);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        return split(lists, 0, lists.length - 1);
    }

    public static ListNode split(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = (left + right) >>> 1;
        ListNode leftHead = split(lists, left, mid);
        ListNode rightHead = split(lists, mid + 1, right);
        ListNode newHead = mergeTwoList(leftHead, rightHead);
        return newHead;
    }

    public static ListNode mergeTwoList(ListNode left, ListNode right) {
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
}
