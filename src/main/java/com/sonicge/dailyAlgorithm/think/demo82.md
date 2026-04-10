* 【字符串解码】
* 输入：s = "3[a]2[bc]"
* 输出："aaabcbc"
*
* 输入：s = "2[abc]3[cd]ef"
* 输出："abcabccdcdcdef"
*
* 输入：s = "abc3[cd]xyz"
* 输出："abccdcdcdxyz"

思路：

1.使用两个栈，一个栈存储数字，另一栈是用来存之前的字符串的

2.如果遇到数字字符的话，直接更新num值，累乘法。

3.如果遇到左括号字符的话，需要将num入栈，然后将currentSb入栈，这里的sb是还没处理完的外层前缀；然后将num 和 currentSb初始化

4.如果遇到右括号字符的话，需要将num出栈，sb出栈。然后开始处理，[]里面的字符串在currentSb中，然后对sb进行拼接！拼接之后更新currentSb

5.如果遇到普通字符的话，直接将普通字符拼接到currentSb中!
