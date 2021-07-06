import jdk.internal.jshell.tool.resources.l10n;

/*
 * @lc app=leetcode.cn id=59 lang=java
 *
 * [59] 螺旋矩阵 II
 *
 * https://leetcode-cn.com/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (79.64%)
 * Likes:    434
 * Dislikes: 0
 * Total Accepted:    109K
 * Total Submissions: 136.9K
 * Testcase Example:  '3'
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：[[1]]
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
    public int[][] generateMatrix(int n) {
        int[][] direction = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int[][] res = new int[n][n];
        int num = n * n;
        int nowX = 0, nowY = 0;
        int dirIndex = 0;
        for (int i = 0; i < num; i++) {
            res[nowX][nowY] = i + 1;
            // System.out.println(res[nowX][nowY]);
            int nextX = nowX + direction[dirIndex][0];
            int nextY = nowY + direction[dirIndex][1];
            if (nextX >= n || nextX < 0 || nextY >= n || nextY < 0 || res[nextX][nextY] != 0) {
                dirIndex = (dirIndex + 1) % 4;
                nextX = nowX + direction[dirIndex][0];
                nextY = nowY + direction[dirIndex][1];
            }
            nowX = nextX;
            nowY = nextY;
        }
        return res;
    }
}
// @lc code=end
