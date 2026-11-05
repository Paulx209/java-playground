package com.sonicge.dailyAlgorithm;

import com.sonicge.test.Test;

/**
 * 【删除排序链表中的重复元素I II】
 *@Author: sonicge
 *@CreateTime: 2026-11-05
 */

public class Demo124 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode res = deleteDuplicates_version1(node1);
        printfListNodes(res);

    }

    /**
     * 删除排序链表中的重复元素I
     * 重复的元素保留一个
     */
    public static ListNode deleteDuplicates_version1(ListNode head){
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode tmp = dummyHead;
        while(tmp.next != null && tmp.next.next!=null){
            if (tmp.next.val == tmp.next.next.val){
                //至少保留一个重复的节点
                tmp.next = tmp.next.next;
            }else{
                tmp = tmp.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 删除排序链表中的重复元素II
     * 重复的元素不保留
     */
    public static ListNode deleteDuplicates_version2(ListNode head) {
        //删除排序链表中的重复元素 由于排序 所以可以保证重复的元素都是连在一起的 但是至少要保留一个
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode tmp = dummyHead;
        while (tmp.next != null && tmp.next.next != null) {
            if(tmp.next.val == tmp.next.next.val){
                // 获取到重复节点的值 只要值相等 直接跳过 一个也不保留
                int val = tmp.next.val;
                while(tmp.next!=null && val == tmp.next.val){
                    tmp.next = tmp.next.next;
                }
            }else{
                tmp = tmp.next;
            }
        }
        return dummyHead.next;
    }

    public static void printfListNodes(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
