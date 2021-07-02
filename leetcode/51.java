import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N 皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (73.80%)
 * Likes:    921
 * Dislikes: 0
 * Total Accepted:    132.9K
 * Total Submissions: 180.2K
 * Testcase Example:  '4'
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 
 * 
 * 
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：[["Q"]]
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
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        dfs(new ArrayList<Integer>(), n);
        return res;
    }

    public void dfs(List<Integer> list, int n) {
        if (list.size() == n) {
            // 所有节点都符合条件
            List<String> ans = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                char[] line = new char[n];
                Arrays.fill(line, '.');
                line[list.get(i)] = 'Q';
                ans.add(new String(line));
            }
            res.add(ans);
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
