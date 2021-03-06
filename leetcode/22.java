import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (77.15%)
 * Likes:    1791
 * Dislikes: 0
 * Total Accepted:    275.8K
 * Total Submissions: 357.5K
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：["()"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> generateParenthesis(int n) {
        char[] strs = new char[n * 2];
        List<String> res = new ArrayList<String>();
        genParenthesisDfs(strs, res, 0, n, n);
        return res;
    }

    public void genParenthesisDfs(char[] strs, List<String> res, int index, int leftLeft, int rightLeft) {
        if (index == strs.length) {
            res.add(new String(strs, 0, strs.length));
            return;
        }
        if (leftLeft > 0) {
            strs[index] = '(';
            genParenthesisDfs(strs, res, index + 1, leftLeft - 1, rightLeft);
        }

        if (leftLeft < rightLeft) {
            strs[index] = ')';
            genParenthesisDfs(strs, res, index + 1, leftLeft, rightLeft - 1);
        }
    }
}
// @lc code=end
