/*
 * @lc app=leetcode.cn id=79 lang=java
 *
 * [79] 单词搜索
 *
 * https://leetcode-cn.com/problems/word-search/description/
 *
 * algorithms
 * Medium (45.26%)
 * Likes:    950
 * Dislikes: 0
 * Total Accepted:    188.2K
 * Total Submissions: 415.6K
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false
 * 。
 * 
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
 * "ABCCED"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
 * "SEE"
 * 输出：true
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word =
 * "ABCB"
 * 输出：false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == board.length
 * n = board[i].length
 * 1 
 * 1 
 * board 和 word 仅由大小写英文字母组成
 * 
 * 
 * 
 * 
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 * 
 */

// @lc code=start
class Solution {
    int[][] direction = new int[][]{{1, 0}, {0,1}, {-1, 0}, {0, -1}};
    
    public boolean exist(char[][] board, String word) {
        char[] wordStr = word.toCharArray();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j ++) {
                boolean res = dfs(board, i, j, wordStr, 0, visited);
                if (res) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int x, int y, char[] value, int index, boolean[][] visited) {
        if (index == value.length) {
            return true;
        }

        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }

        if (visited[x][y] || board[x][y] != value[index]) {
            return false;
        }

        visited[x][y] = true;
        // 往4个方向进行dfs遍历
        for (int i = 0; i < 4; i ++) {
            boolean res = dfs(board, x + direction[i][0], y + direction[i][1], value, index + 1, visited);
            if (res) {
                return res;
            }
        }
        visited[x][y] = false;
        return false;
    }
}
// @lc code=end

