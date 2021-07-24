/*
 * @lc app=leetcode.cn id=43 lang=java
 *
 * [43] 字符串相乘
 *
 * https://leetcode-cn.com/problems/multiply-strings/description/
 *
 * algorithms
 * Medium (44.71%)
 * Likes:    651
 * Dislikes: 0
 * Total Accepted:    145.4K
 * Total Submissions: 324.9K
 * Testcase Example:  '"2"\n"3"'
 *
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 
 * 示例 1:
 * 
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 
 * 示例 2:
 * 
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 
 * 说明：
 * 
 * 
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        // 字符串数组
        char[] char1Array, char2Array;
        if (num1.length() > num2.length()) {
            char1Array = num1.toCharArray();
            char2Array = num2.toCharArray();
        } else {
            char1Array = num2.toCharArray();
            char2Array = num1.toCharArray();
        }

        int[] num1Array = new int[char1Array.length];
        for (int i = 0; i < char1Array.length; i++) {
            num1Array[i] = char1Array[i] - '0';
        }

        // for (int i = 0; i < num1Array.length; i++) {
        //     System.out.println(num1Array[i]);
        // }

        int[] num2Array = new int[char2Array.length];
        for (int i = 0; i < num2Array.length; i++) {
            num2Array[i] = char2Array[i] - '0';
        }

        // for (int i = 0; i < num2Array.length; i++) {
        //     System.out.println(num2Array[i]);
        // }
        int allSize = num1Array.length + num2Array.length - 2;
        for (int i = num1Array.length - 1; i >= 0; i--) {
            for (int j = num2Array.length - 1; j >= 0; j--) {
                res[allSize - i - j] += num1Array[i] * num2Array[j];
            }
        }

        // for (int i = 0; i < res.length; i++) {
        //     System.out.println(res[i]);
        // }

        for (int i = 0; i < res.length; i++) {
            if (res[i] >= 10) {
                res[i + 1] += (res[i] / 10);
                res[i] = res[i] % 10;
            }
            res[i] += '0';
        }

        StringBuffer sb = new StringBuffer();

        int index = res.length - 1;
        while (index >= 0 && res[index] == '0')
            index--;
        while (index >= 0) {
            sb.append((char) res[index--]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

}
// @lc code=end
