/*
 * @lc app=leetcode.cn id=96 lang=java
 *
 * [96] 不同的二叉搜索树
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees/description/
 *
 * algorithms
 * Medium (69.74%)
 * Likes:    1229
 * Dislikes: 0
 * Total Accepted:    139.3K
 * Total Submissions: 199.7K
 * Testcase Example:  '3'
 *
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：5
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
 * 
 * 
 */

// @lc code=start
class Solution {

    // 利用记忆化搜索进行加速
    public int numTrees(int n) {
        int[][] resNums = new int[n + 1][n + 1];
        return getNumTrees(resNums, 1, n);
    }

    public int getNumTrees(int[][] resNums, int start, int end) {
        if (start >= end) {
            return 1;
        }
        if (resNums[start][end] != 0) {
            return resNums[start][end];
        }
        int res = 0;
        for (int i = start; i <= end; i++) {
            int leftCount = getNumTrees(resNums, start, i - 1);
            int rightCount = getNumTrees(resNums, i + 1, end);
            res += (leftCount * rightCount);
        }
        resNums[start][end] = res;
        return res;
    }
}
// @lc code=end
