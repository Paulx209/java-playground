package com.sonicge.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 合并两个链表  升序排列
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-09
 */

public class Test {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 链表初始化
     * @return ListNode[]
     */
    public static ListNode[] init() {
        Scanner sc = new Scanner(System.in);

        int size1 = sc.nextInt();
        ListNode fakeHead = new ListNode(0);
        ListNode temp = fakeHead;
        for (int i = 0; i < size1; i++) {
            ListNode node = new ListNode(sc.nextInt());
            temp.next = node;
            temp = temp.next;
        }

        int size2 = sc.nextInt();
        ListNode fakeHead2 = new ListNode(0);
        ListNode temp2 = fakeHead2;
        for (int i = 0; i < size2; i++) {
            ListNode node = new ListNode(sc.nextInt());
            temp2.next = node;
            temp2 = temp2.next;
        }
        return new ListNode[]{fakeHead.next, fakeHead2.next};
    }

    /**
     * 合并两个链表【升序】
     * @param listNodes
     * @return
     */
    public static ListNode mergeListNodes(ListNode[] listNodes) {
        ListNode newHead = new ListNode(0);
        ListNode temp = newHead;
        ListNode cur1 = listNodes[0];
        ListNode cur2 = listNodes[1];

        while (cur1 != null || cur2 != null) {
            int val1 = cur1!=null ? cur1.val : Integer.MAX_VALUE;
            int val2 = cur2!=null ? cur2.val : Integer.MAX_VALUE;
            if(val1 < val2){
                temp.next = new ListNode(val1);
                temp = temp.next;
                cur1 = cur1.next;
            }else{
                temp.next = new ListNode(val2);
                temp = temp.next;
                cur2 = cur2.next;
            }
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        StringBuilder sb =new StringBuilder();
        //[1 3 5 7]  [2 4 6 8 ]
        ListNode[] nodes = init();
        ListNode res = mergeListNodes(nodes);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }
}
