* 【N字形变换】
* <p>
* 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
* <p>
* 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
* <p>
* P   A   H   N
* A P L S I I G
* Y   I   R
* 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

思路：
1.对原始字符串中的字符进行挨个遍历，然后判断该字符是属于哪一行，每一行会对应一个StringBuilder。
2.从上到下，index+=flag flag为1；但是为了规律，我们要把flag置为-1，然后判断当前的index=0，然后将flag = -flag;
3.从下到上，flag变为-1，index+=flag，就是--
