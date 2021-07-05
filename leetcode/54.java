import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=54 lang=java
 *
 * [54] 螺旋矩阵
 *
 * https://leetcode-cn.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (46.94%)
 * Likes:    810
 * Dislikes: 0
 * Total Accepted:    163.7K
 * Total Submissions: 346K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 
 * -100 
 * 
 * 
 */

// @lc code=start
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        // 模拟进行输出
        if (matrix.length == 0) {
            return res;
        }
        int xLimit = matrix.length;
        int yLimit = matrix[0].length;
        int nowX = 0, nowY = 0;
        while (xLimit > 0 && yLimit > 0) {
            // 向右侧旋转出输
            for (int i = 0; i < yLimit - 1; i++) {
                res.add(matrix[nowX][nowY]);
                nowY++;
            }
            if (xLimit == 1) {
                res.add(matrix[nowX][nowY]);
                break;
            }
            // 向下侧旋转输出
            for (int i = 0; i < xLimit - 1; i++) {
                res.add(matrix[nowX][nowY]);
                nowX++;
            }
            if (yLimit == 1) {
                res.add(matrix[nowX][nowY]);
                break;
            }
            // 向左侧旋转输出
            for (int i = 0; i < yLimit - 1; i++) {
                res.add(matrix[nowX][nowY]);
                nowY--;
            }
            // 向上侧旋转输出
            for (int i = 0; i < xLimit - 1; i++) {
                res.add(matrix[nowX][nowY]);
                nowX--;
            }
            nowX++;
            nowY++;
            xLimit -= 2;
            yLimit -= 2;
        }

        return res;
    }
}
