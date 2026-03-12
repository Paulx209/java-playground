package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【旋转链表】
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-12
 */

public class Demo50 {
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

    public void readAllNode(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    /**
     * 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        //由于k的长度可能比较大 所以要先计算出链表的长度
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = head;
        int size = 0;
        ListNode lastNode = null;
        while (temp != null) {
            if(temp.next==null)lastNode = temp;
            temp = temp.next;
            size++;
        }
        //开始旋转链表，其实就是让从下标为x的节点开始分成两段，第二段的最后指向第一段的开头
        int index = size - (k % size);
        temp = dummyHead;
        for(int i =0;i<index;i++){
            temp = temp.next;
        }
        ListNode newStart = temp.next;
//        dummyHead.next = newStart;
        temp.next = null;
        lastNode.next = dummyHead.next;
        return newStart;
    }

    public static void main(String[] args) {
        Demo50 demo50 = new Demo50();
        ListNode listNode = demo50.initNodeList();
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ListNode newHead = demo50.rotateRight(listNode, k);
        demo50.readAllNode(newHead);
    }
}
