import jdk.javadoc.internal.tool.Start;

/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (43.02%)
 * Likes:    1428
 * Dislikes: 0
 * Total Accepted:    161K
 * Total Submissions: 374.2K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 
 * 
 * 
 * 
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 
 * 
 * 
 * 
 * 
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 
 */

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {
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
