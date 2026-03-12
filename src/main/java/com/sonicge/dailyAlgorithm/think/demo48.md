* 【k个一组翻转链表】
* 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
*
* 输入：head = [1,2,3,4,5], k = 2
* 输出：[2,1,4,3,5]

思路： （本题的难度为困难，但是其实掌握思路之后就比较好做了）

1.首先计算出链表的总长度，然后每次翻转k个元素，其实比较难得就是维护pre和cur的状态

2.最开始pre和cur都为dummyHead，也就是head元素的前一个节点。更新cur到该范围链表的最后一个节点，记录cur.next为nextHead

3.cur.next = null ;然后翻转链表，记录pre.next为nextPre，然后nextPre.next = nextHead; pre.next = newHead

4.return dummyHead.next
