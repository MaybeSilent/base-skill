/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (33.85%)
 * Likes:    3495
 * Dislikes: 0
 * Total Accepted:    544.4K
 * Total Submissions: 1.6M
 * Testcase Example:  '"babad"'
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "cbbd"
 * 输出："bb"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "a"
 * 输出："a"
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：s = "ac"
 * 输出："a"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 仅由数字和英文字母（大写和/或小写）组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        int max = 0;
        char[] strs = s.toCharArray();
        String result = new String();
        for (int i = 0; i < strs.length; i ++) {
            // 奇数的情况
            int index = 0;
            for (index = 1; i + index < strs.length && i - index >= 0; index ++) {
                if (strs[i - index] != strs[i + index]) {
                    break;
                }
            }
            index --;
            if (index * 2 + 1 > max) {
                max = index * 2 + 1;
                result = s.substring(i - index, i + index + 1);
            }

            // 偶数的情况
            if (i + 1 < strs.length && strs[i+1] == strs[i]) {
                int j = i + 1;
                index = 1;
                while (i - index >= 0 && j + index < strs.length) {
                    if (strs[i - index] != strs[j + index]) {
                        break;
                    }
                    index ++;
                }
                index --;
                if (index*2 + 2 > max) {
                    max = index * 2 + 2;
                    result = s.substring(i - index, j + index + 1);
                }
            }
        }
        return result;
    }
}
// @lc code=end

