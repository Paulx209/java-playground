package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【k个一组翻转链表】
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * @Author: sonicge
 * @CreateTime: 2026-03-12
 */

public class Demo48 {
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
     * k个一组翻转链表
     *
     * @param head 头结点
     * @param k    k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        int size = 0;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        ListNode pre = dummyHead;
        ListNode cur = dummyHead;
        while (size >= k) {
            for (int i = 0; i < k; i++) {
                cur = cur.next;
            }
            ListNode nextHead = cur.next;
            cur.next = null;
            ListNode newHead = reverse(pre.next);
            ListNode nextPre = pre.next;
            nextPre.next=nextHead;
            pre.next = newHead;
            //更新pre和cur
            pre=nextPre;
            cur=nextPre;
            size-=k;
        }
        return dummyHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = cur;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static void main(String[] args) {
        Demo48 demo48 = new Demo48();
        ListNode listNode = demo48.initNodeList();
        Scanner sc = new Scanner(System.in);
        ListNode newNode = demo48.reverseKGroup(listNode, sc.nextInt());
        ListNode temp = newNode;
        while(temp!=null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}
