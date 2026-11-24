package com.sonicge.dailyAlgorithm;

/**
 * 【链表的奇偶重排】
 *@Author: sonicge
 *@CreateTime: 2026-11-24
 */

public class Demo129 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(7);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //执行函数 输出
        ListNode res = addEventList(head);
        while(res != null){
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 奇偶重排
     * @param head
     * @return 新列表
     */
    public static ListNode addEventList(ListNode head) {
        //奇节点重开一路；偶数节点重开一路
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pre = dummyHead;

        ListNode newHead = new ListNode(0);
        ListNode other = newHead;

        int index = 1;
        while(pre.next != null){
            // 处理奇数节点
            if(index % 2 != 0){
                pre = pre.next;
            }else{
                //处理偶数节点
                ListNode node = pre.next;
                other.next = node;
                other = other.next;
                pre.next = pre.next.next;
            }
            index++;
        }
        // 如果只有奇数个节点的话 那最后一个other的末尾应该是挂了一个节点
        other.next = null;

        // 开始合并
        ListNode temp = dummyHead;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newHead.next;
        return dummyHead.next;
    }
}
