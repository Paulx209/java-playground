package com.sonicge.dailyAlgorithm;

/**
 * 【两两交换链表中的节点】
 * 给定一个链表，然后每次将两个相邻的节点交换，并且返回交换后链表的头结点
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-21
 */

public class Demo102 {
    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }
    public static ListNode initListNode(){
        return new ListNode(0);
    }

    /**
     * 两两交换
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        while(pre.next !=null && pre.next.next!=null){
            ListNode first = pre.next;
            ListNode second = pre.next.next;
            ListNode newHead = second.next;

            first.next = null;
            second.next = first;
            pre.next = second;
            first.next = newHead;

            pre = pre.next.next;
        }
        return  dummyHead.next;
    }
    public static void main(String[] args) {
        //1.初始化
        ListNode listNode = initListNode();

        //2.两两交换
        ListNode res = swapPairs(listNode);

    }
}
