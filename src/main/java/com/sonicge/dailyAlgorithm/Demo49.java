package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【删除排序链表中的重复元素 II】
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-12
 */

public class Demo49 {
    public class ListNode {
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

    public ListNode initNodeList() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        ListNode head = new ListNode(sc.nextInt());
        ListNode temp = head;
        for (int i = 1; i < size; i++) {
            temp.next = new ListNode(sc.nextInt());
            temp = temp.next;
        }
        return head;
    }

    /**
     * 删除排序链表中的重复元素 II
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            if (val == cur.next.next.val) {
                //如果值和下一个相等的话
                while(cur.next.next!=null && val == cur.next.next.val){
                    cur.next =cur.next.next;
                }
                //避免只跳过一个相等元素的情况
                cur.next = cur.next.next;
            } else {
                //如果值不相等的话
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    public void readAllNode(ListNode head){
        ListNode temp = head;
        while(temp!=null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Demo49 demo49 = new Demo49();
        ListNode listNode = demo49.initNodeList();
        ListNode newNode = demo49.deleteDuplicates(listNode);
        demo49.readAllNode(newNode);
    }
}
