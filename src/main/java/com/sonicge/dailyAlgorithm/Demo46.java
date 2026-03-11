package com.sonicge.dailyAlgorithm;

import java.util.Scanner;

/**
 * 【合并两个有序链表】
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * @Author: sonicge
 * @CreateTime: 2026-03-11
 */

public class Demo46 {

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

    public void initNodeList() {
        Scanner sc = new Scanner(System.in);
        int size1 = sc.nextInt();
        int size2 = sc.nextInt();
        ListNode head1 = new ListNode(sc.nextInt());
        ListNode temp1 = head1;
        for (int i = 0; i < size1 - 1; i++) {
            temp1.next = new ListNode(sc.nextInt());
            temp1 = temp1.next;
        }
        ListNode head2 = new ListNode(sc.nextInt());
        ListNode temp2 = head2;
        for (int i = 0; i < size2 - 1; i++) {
            temp2.next = new ListNode(sc.nextInt());
            temp2 = temp2.next;
        }
        ListNode listNode = mergeTwoLists(head1, head2);
        ListNode temp = listNode;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }

    }

    public static void main(String[] args) {

        Demo46 demo46 = new Demo46();
        demo46.initNodeList();

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        while (list1 != null || list2 != null) {
            int val1 = list1 != null ? list1.val : 200;
            int val2 = list2 != null ? list2.val : 200;
            if (val1 < val2) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        return dummyHead.next;
    }
}
