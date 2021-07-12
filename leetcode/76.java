/*
 * @lc app=leetcode.cn id=76 lang=java
 *
 * [76] 最小覆盖子串
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (41.73%)
 * Likes:    1229
 * Dislikes: 0
 * Total Accepted:    151.4K
 * Total Submissions: 362.7K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 和 t 由英文字母组成
 * 
 * 
 * 
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */

// @lc code=start
class Solution {
    public String minWindow(String s, String t) {
        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();

        int count = tStr.length;
        int[] tStrCount = new int[256];

        for (int i = 0; i < tStr.length; i++) {
            tStrCount[tStr[i]]++;
        }
        int resStart = 0, resEnd = Integer.MAX_VALUE;

        int start = 0, end = 0;
        while (end < sStr.length) {
            if (tStrCount[sStr[end]] > 0) {
                count--;
            }

            tStrCount[sStr[end]]--;

            if (count == 0) {
                while (tStrCount[sStr[start]] < 0) {
                    tStrCount[sStr[start]] += 1;
                    start++;
                }
                if (end - start < resEnd - resStart) {
                    resEnd = end;
                    resStart = start;
                }
            }

            end++;
        }

        return resEnd == Integer.MAX_VALUE ? "" : s.substring(resStart, resEnd + 1);
    }
}
// @lc code=end
