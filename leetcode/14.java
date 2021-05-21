/*
 * @lc app=leetcode.cn id=14 lang=java
 *
 * [14] 最长公共前缀
 *
 * https://leetcode-cn.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (39.53%)
 * Likes:    1597
 * Dislikes: 0
 * Total Accepted:    517.2K
 * Total Submissions: 1.3M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * 0 
 * strs[i] 仅由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        char[] res = strs[0].toCharArray();
        int length = res.length;

        for (int i = 1; i < strs.length && length > 0; i++) {
            char[] compare = strs[i].toCharArray();
            if (compare.length < length) {
                length = compare.length;
            }
            for (int j = 0; j < length; j++) {
                if (res[j] != compare[j]) {
                    length = j;
                    break;
                }
            }
        }
        return new String(res, 0, length);
    }
}
// @lc code=end
