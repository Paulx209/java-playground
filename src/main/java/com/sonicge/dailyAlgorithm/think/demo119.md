# 【算法名称】

最大数

https://leetcode.cn/problems/largest-number/description/

给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。

注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

## 【输入输出】
```text
示例 1：

输入：nums = [10,2]
输出："210"
示例 2：

输入：nums = [3,30,34,5,9]
输出："9534330"
```

## 【思路】

1. 要组成一个最大数，正确的排序是：a+b > b+a ， 看拼接起来的字符串谁大谁小。可以采用compareTo方法来比较，逐位比较字符

2. Arrays.sort(字符串数组，comparator排序器)，对于comparator排序器，(a,b) -> return (a-b); 如果返回的是正数的话，排名为 b a ；如果范蝴蝶是负数的话，排名为 a b，记住这个铁律

3. (a,b) -> {},a b是两个字符串，如果 return (a+b).compareTo(b+a)， a+b大的话，b就在a的前面，但应该是a在b的前面；所以正确答案是 return (b+a).compareTo(a+b)
