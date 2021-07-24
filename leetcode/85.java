/*
 * @lc app=leetcode.cn id=85 lang=java
 *
 * [85] 最大矩形
 *
 * https://leetcode-cn.com/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (51.73%)
 * Likes:    963
 * Dislikes: 0
 * Total Accepted:    86.3K
 * Total Submissions: 166.8K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：matrix =
 * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：matrix = []
 * 输出：0
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：matrix = [["0"]]
 * 输出：0
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：matrix = [["1"]]
 * 输出：1
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 
 * matrix[i][j] 为 '0' 或 '1'
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximalRectangle(char[][] matrix) {
        // 从二维数组中获得相应的height数组
        if (matrix.length == 0) {
            return 0;
        }

        int[] heights = new int[matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }

            res = Math.max(getLargestArea(heights), res);
        }

        return res;
    }

    public int getLargestArea(int[] heights) {
        int[] stack = new int[heights.length + 1]; // 构造一个递增的栈来解决面积
        stack[0] = -1;
        int index = 0;

        int res = 0; // 记录结果res
        for (int i = 0; i < heights.length; i++) {
            if (index > 0 && heights[i] < heights[stack[index]]) {
                while (index > 0 && heights[i] < heights[stack[index]]) {
                    res = Math.max(heights[stack[index]] * (i - stack[index - 1] - 1), res);
                    index--;
                }
            }
            stack[++index] = i;
        }

        while (index > 0) {
            res = Math.max(heights[stack[index]] * (heights.length - stack[index - 1] - 1), res);
            index--;
        }

        return res;
    }
}
// @lc code=end
