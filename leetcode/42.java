/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (55.82%)
 * Likes:    2405
 * Dislikes: 0
 * Total Accepted:    257.2K
 * Total Submissions: 458.7K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == height.length
 * 0 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        // for (int i = 0; i < leftMax.length; i++) {
        // System.out.print(leftMax[i] + " ");
        // }

        // System.out.println();

        // for (int i = 0; i < rightMax.length; i++) {
        // System.out.print(rightMax[i] + " ");
        // }

        // System.out.println();

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            // System.out.print(res + " ");
            int val = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (val > 0) {
                res += val;
            }
        }
        return res;
    }
}
// @lc code=end
