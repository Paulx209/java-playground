* 【最小栈】
* 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
* 实现 MinStack 类:
* MinStack() 初始化堆栈对象。
*      void push(int val) 将元素val推入堆栈。
*      void pop() 删除堆栈顶部的元素。
*      int top() 获取堆栈顶部的元素。
*      int getMin() 获取堆栈中的最小元素。

思路：

1. 维护两个栈，一个是普通的栈numStack，另一个是最小数的栈minStack

2. 给每一个num都对应一个最小值，numStack的size 和 minStack的size是一样的。所以每次push的时候，minStack会取当前栈顶元素和val的最小值放进去
