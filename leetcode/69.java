/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 *
 * https://leetcode-cn.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (39.30%)
 * Likes:    707
 * Dislikes: 0
 * Total Accepted:    335K
 * Total Submissions: 852.5K
 * Testcase Example:  '4'
 *
 * 实现 int sqrt(int x) 函数。
 * 
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 
 * 示例 1:
 * 
 * 输入: 4
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842..., 
 * 由于返回类型是整数，小数部分将被舍去。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 二分相关
    public int mySqrt(int x) {
        // 进行开方
        long start = 0, end = x;
        while (start <= end) {
            long mid = (end - start) / 2 + start;
            if (mid * mid > x) {
                end = mid - 1;
            } else if (mid * mid < x) {
                start = mid + 1;
            } else {
                return (int)mid;
            }
        }
        return (int)end;
    }
}
// @lc code=end
