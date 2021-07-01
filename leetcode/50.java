/*
 * @lc app=leetcode.cn id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode-cn.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (37.49%)
 * Likes:    677
 * Dislikes: 0
 * Total Accepted:    193K
 * Total Submissions: 513.9K
 * Testcase Example:  '2.00000\n10'
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^-2 = 1/2^2 = 1/4 = 0.25
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * -100.0 
 * -2^31 
 * -10^4 
 * 
 * 
 */

// @lc code=start
class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;

        int absN = Math.abs(n);
        // System.out.println(absN);

        // 对TLE进行特殊处理
        double val = x;
        double res = 1.0;
        while (absN != 0) {
            if ((absN & 1) != 0)
                res *= val;
            val *= val;
            absN >>>= 1;
        }

        return n < 0 ? 1 / res : res;
    }
}
// @lc code=end
