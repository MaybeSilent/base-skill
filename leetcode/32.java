/*
 * @lc app=leetcode.cn id=32 lang=java
 *
 * [32] 最长有效括号
 *
 * https://leetcode-cn.com/problems/longest-valid-parentheses/description/
 *
 * algorithms
 * Hard (34.94%)
 * Likes:    1316
 * Dislikes: 0
 * Total Accepted:    153.2K
 * Total Submissions: 437.9K
 * Testcase Example:  '"(()"'
 *
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = ""
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * s[i] 为 '(' 或 ')'
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestValidParentheses(String s) {
        int[] stack = new int[s.length()];
        int index = 0;

        int res = 0;
        char[] strs = s.toCharArray();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == '(') {
                stack[index++] = i;
            } else {
                if (index > 0 && strs[stack[index - 1]] == '(') {
                    index--; //
                    if (index > 0) {
                        res = Math.max(res, i - stack[index - 1]);
                    } else {
                        res = Math.max(res, i + 1);
                    }
                } else {
                    stack[index++] = i;
                }
            }
        }
        return res;
    }
}
// @lc code=end
