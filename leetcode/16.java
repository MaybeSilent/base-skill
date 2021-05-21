import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=16 lang=java
 *
 * [16] 最接近的三数之和
 *
 * https://leetcode-cn.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (45.94%)
 * Likes:    777
 * Dislikes: 0
 * Total Accepted:    215.8K
 * Total Submissions: 469.5K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {

    public int threeSumClosest(int[] nums, int target) {
        int res = 0, min = Integer.MAX_VALUE;
        Arrays.sort(nums); // nums排序
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (Math.abs(nums[i] + nums[left] + nums[right] - target) < min) {
                    min = Math.abs(nums[i] + nums[left] + nums[right] - target);
                    res = nums[i] + nums[left] + nums[right];
                }
                if (nums[i] + nums[left] + nums[right] - target > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return res;
    }
}
// @lc code=end
