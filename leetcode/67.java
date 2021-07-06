/*
 * @lc app=leetcode.cn id=67 lang=java
 *
 * [67] 二进制求和
 *
 * https://leetcode-cn.com/problems/add-binary/description/
 *
 * algorithms
 * Easy (54.49%)
 * Likes:    637
 * Dislikes: 0
 * Total Accepted:    176.3K
 * Total Submissions: 323.6K
 * Testcase Example:  '"11"\n"1"'
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 
 * 示例 2:
 * 
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String addBinary(String a, String b) {
        char[] aStr = reverseChar(a.toCharArray());
        char[] bStr = reverseChar(b.toCharArray());

        int len = Math.max(aStr.length, bStr.length);

        int[] res = new int[len + 1];
        int add = 0;

        for (int i = 0; i < res.length; i++) {
            res[i] += add;
            if (i < aStr.length && aStr[i] == '1')
                res[i]++;
            if (i < bStr.length && bStr[i] == '1')
                res[i]++;
            add = res[i] / 2;
            res[i] = res[i] % 2;
        }

        StringBuffer sb = new StringBuffer();
        int index = res.length - 1;
        while (index >= 0 && res[index] == 0)
            index--;
        while (index >= 0) {
            sb.append((char) (res[index--] + '0'));
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    private char[] reverseChar(char[] reverse) {
        int start = 0, end = reverse.length - 1;
        char temp;
        while (start < end) {
            temp = reverse[end];
            reverse[end] = reverse[start];
            reverse[start] = temp;
            start++;
            end--;
        }
        return reverse;
    }
}
// @lc code=end
