package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【反转链表II】
 * 反转链表II是在反转链表的基础上做了升级，不是从头到尾挨个反转，而是只翻转部分的，所以参数会多一个[left,right]
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-12
 */

public class Demo47 {
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
        int size = sc.nextInt(); //5
        ListNode head = new ListNode(sc.nextInt());
        ListNode temp = head;
        for (int i = 1; i < size; i++) {
            temp.next = new ListNode(sc.nextInt());
            temp = temp.next;
        }
        return head;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode leftNode = dummyNode;
        ListNode rightNode = dummyNode;
        for(int i=0;i<right;i++){
            if(i<left-1)leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        //leftNode就是左边界的前一个node
        ListNode tail = rightNode.next;
        rightNode.next = null;
        ListNode newHead = reverse(leftNode.next);
        leftNode.next.next = tail;
        leftNode.next = newHead;
        return dummyNode.next;
    }
    public ListNode reverse(ListNode head){
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
        Demo47 demo47 = new Demo47();
        ListNode listNode = demo47.initNodeList();
        Scanner sc = new Scanner(System.in);
        int left = sc.nextInt();
        int right = sc.nextInt();
        ListNode res = demo47.reverseBetween(listNode, left, right);
        ListNode temp =res;
        while(temp!=null){
            System.out.println(temp.val);
        }
    }
}
