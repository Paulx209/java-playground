package com.sonicge.dailyAlgorithm;


/**
 * 【重排链表】
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *@Author: sonicge
 *@CreateTime: 2026-04-14
 */

public class Demo101 {
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
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        reorderList(node1);
    }
    public static void reorderList(ListNode head) {
        //1.找到中点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;
        while(fast !=null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //2.将后半部分翻转
        ListNode temp = slow.next;
        slow.next = null;
        ListNode newHead = reverse(temp);
        //3.合并链表
        ListNode head1 = head;
        ListNode head2 = newHead;
        merge(head1, head2);

        //输出结果
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }

    }
    public static void  merge(ListNode head1,ListNode head2){
        // 1 2 3  | 4 5
        while(head2!=null){
            ListNode head1Next = head1.next;
            ListNode head2Next = head2.next;

            // 1 4 2  | 3 5
            head1.next = head2;
            head2.next = head1Next;

            head1 = head1Next;
            head2 = head2Next;
        }
    }
    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while(cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

}
