import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=45 lang=java
 *
 * [45] 跳跃游戏 II
 *
 * https://leetcode-cn.com/problems/jump-game-ii/description/
 *
 * algorithms
 * Medium (39.89%)
 * Likes:    1013
 * Dislikes: 0
 * Total Accepted:    139.1K
 * Total Submissions: 344.3K
 * Testcase Example:  '[2,3,1,1,4]'
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 
 * 假设你总是可以到达数组的最后一个位置。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: [2,3,0,1,4]
 * 输出: 2
 * 
 * 
 * 
 * 
 * 提示:
 * 
 * 
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int jump(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] minJump = new int[nums.length];
        Arrays.fill(minJump, Integer.MAX_VALUE);
        minJump[0] = 0;
        // 最小的跳数
        for (int i = 0; i < nums.length; i++) {
            int min = minJump[i] + 1;
            for (int j = i; j <= nums[i] + i && j < nums.length; j++) {
                if (minJump[j] > min) {
                    minJump[j] = min;
                }
                if (j == nums.length - 1) {
                    return minJump[nums.length - 1];
                }
            }

        return minJump[nums.length - 1];
    }
}
// @lc code=end
