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
        return manacher(s);
    }

    private String manacher(String s) {
        char[] strs = getCharArray(s);
        int maxMid = 0, maxRight = 0;
        int[] value = new int[strs.length];
        for (int i = 1; i < strs.length - 1; i ++) {
            int length = 1;
            if (i < maxRight) {
                length = Math.min(value[maxMid * 2 - i], maxRight - i);
            }
            while (i + length < strs.length && i - length >= 0 && strs[i+length] == strs[i-length]) {
                length ++;
            }
            value[i] = length - 1;
            if (i + value[i] > maxRight) {
                maxRight = value[i] + i;
                maxMid = i;
            }
        }
        int max = 0;
        for (int i = 0; i < strs.length - 1; i ++) {
            if (value[i] > value[max] || (value[i] == value[max] && (i - value[i]) % 2 != 0)) {
                max = i;
            }
        }
        if (value[max] == 0) {
            return s.length() <= 1 ? s : s.substring(0, 1);
        }

        StringBuffer sb = new StringBuffer();
        for (int i = max - value[max]; i <= max + value[max]; i ++) {
            if (strs[i] != '#') {
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    private char[] getCharArray(String s) {
        char[] result = new char[s.length() * 2 + 1];
        for (int i = 0; i < s.length(); i ++) {
            result[i*2] = '#';
            result[i*2+1] = s.charAt(i);
        }
        result[0] = '$'; result[result.length - 1] = '^';
        return result;
    }

    private String dpPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        boolean[][] dp = new boolean[s.length()][s.length()];
        String result = s.substring(0, 1);
        char[] strs = s.toCharArray();

        for (int i = 0; i < s.length(); i ++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < s.length(); j ++) {
            for (int i = 0; i < j; i ++) {
                dp[i][j] = j == i + 1  ? strs[i] == strs[j] : dp[i+1][j-1] && (strs[i] == strs[j]);
                if (dp[i][j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j+1);
                }
            }
        }

        return result;
    }


    private String forLoopPalindrome(String s) {
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

