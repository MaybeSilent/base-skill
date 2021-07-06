/*
 * @lc app=leetcode.cn id=65 lang=java
 *
 * [65] 有效数字
 *
 * https://leetcode-cn.com/problems/valid-number/description/
 *
 * algorithms
 * Hard (26.74%)
 * Likes:    265
 * Dislikes: 0
 * Total Accepted:    43.4K
 * Total Submissions: 162.3K
 * Testcase Example:  '"0"'
 *
 * 有效数字（按顺序）可以分成以下几个部分：
 * 
 * 
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 
 * 
 * 小数（按顺序）可以分成以下几个部分：
 * 
 * 
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 
 * 
 * 
 * 
 * 整数（按顺序）可以分成以下几个部分：
 * 
 * 
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 
 * 
 * 部分有效数字列举如下：
 * 
 * 
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7",
 * "+6e-1", "53.5e93", "-123.456e789"]
 * 
 * 
 * 部分无效数字列举如下：
 * 
 * 
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 
 * 
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "0"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "e"
 * 输出：false
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "."
 * 输出：false
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：s = ".1"
 * 输出：true
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 状态机跳转模拟
    public boolean isNumber(String s) {
        int state = 0;
        // state : 0
        // 1 : 开始时的正负号
        // 2 : 小数点前数字
        // 3 : 小数点
        // 4 -- : 前面无数字小数点
        // 5 : 小数点后数字
        // 6 : e
        // 7 : +-
        // 8 : 数字
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            switch (state) {
                case 0:
                    if (str[i] == '+' || str[i] == '-') {
                        state = 1;
                    } else if (str[i] >= '0' && str[i] <= '9') {
                        state = 2;
                    } else if (str[i] == '.') {
                        state = 4;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 2;
                    } else if (str[i] == '.') {
                        state = 4;
                    } else {
                        return false;
                    }
                    break;
                case 2: // 2 : 小数点前数字
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 2;
                    } else if (str[i] == '.') {
                        state = 3;
                    } else if (str[i] == 'e' || str[i] == 'E') {
                        state = 6;
                    } else {
                        return false;
                    }
                    break;
                case 3: // 3 : 小数点
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 5;
                    } else if (str[i] == 'e' || str[i] == 'E') {
                        state = 6;
                    } else {
                        return false;
                    }
                    break;
                case 4: // 4 -- : 前面无数字小数点
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 5;
                    } else {
                        return false;
                    }
                    break;
                case 5: // 5 : 小数点后数字
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 5;
                    } else if (str[i] == 'e' || str[i] == 'E') {
                        state = 6;
                    } else {
                        return false;
                    }
                    break;
                case 6:
                    if (str[i] == '+' || str[i] == '-') {
                        state = 7;
                    } else if (str[i] >= '0' && str[i] <= '9') {
                        state = 8;
                    } else {
                        return false;
                    }
                    break;
                case 7:
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 8;
                    } else {
                        return false;
                    }
                    break;
                case 8:
                    if (str[i] >= '0' && str[i] <= '9') {
                        state = 8;
                    } else {
                        return false;
                    }
                    break;
            }
        }

        return state == 2 || state == 3 || state == 5 || state == 8;
    }
}
// @lc code=end
