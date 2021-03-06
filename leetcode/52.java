import java.util.ArrayList;

/*
 * @lc app=leetcode.cn id=52 lang=java
 *
 * [52] N皇后 II
 *
 * https://leetcode-cn.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (82.34%)
 * Likes:    275
 * Dislikes: 0
 * Total Accepted:    67.5K
 * Total Submissions: 82K
 * Testcase Example:  '4'
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 4
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    int res = 0;

    public int totalNQueens(int n) {
        dfs(new ArrayList<>(), n);
        return res;
    }

    public void dfs(List<Integer> list, int n) {
        if (list.size() == n) {
            res++;
        }
        // dfs遍历可能的条件
        for (int i = 0; i < n; i++) {
            int lineX = list.size();
            boolean valid = true;

            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == i || Math.abs(lineX - j) == Math.abs(list.get(j) - i)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                list.add(i);// 进行下一次的遍历
                dfs(list, n);
                list.remove(list.size() - 1);
            }
        }
    }
}
// @lc code=end
