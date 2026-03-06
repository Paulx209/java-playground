* 【插入区间】
* 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，
* 其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。
* <p>
* 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，
* 且区间之间不重叠（如果有必要的话，可以合并区间）。
* <p>
* 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
* 输出：[[1,2],[3,10],[12,16]]
* 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。

思路：

1.处理的思路是这样的，由于我们要将这个区间插入进去，那我们就要把intervals[i][1] < newInterval[0] ，这部分数组都放入res中

2.然后着重处理 intervals[i][0] <=newInterval[1] 这部分的数组元素，直接对他们进行合并，也就是更新newInterval[0] 和 newInterval[1]。有一个点需要记住，更新的时候需要将数组添加到res中

3.最后将最右部分和newInterval无关的数组都添加到res中
