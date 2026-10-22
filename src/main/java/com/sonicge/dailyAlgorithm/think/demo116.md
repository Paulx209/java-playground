# 【算法名称】

用栈实现队列。 https://leetcode.cn/problems/implement-queue-using-stacks/description/

## 【输入输出】
示例：

```text
输入：
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
输出：
[null, null, null, 1, 1, false]
```

## 【思路】

1. 使用两个栈来实现队列，由于栈具有后进先出的特点，所以两次后进先出就变成了先进先出

2. 基本的思路是，如果有元素进入的话，只放在inStack中；如果有元素出去的话，需要判断outStack是否为空，不为空的话，说明之前排位靠前的元素还在里面，不需要转换；如果为空的话，说明所有的元素都已经排出去了，需要转换

