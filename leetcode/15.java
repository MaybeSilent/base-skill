import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 *
 * https://leetcode-cn.com/problems/3sum/description/
 *
 * algorithms
 * Medium (31.99%)
 * Likes:    3332
 * Dislikes: 0
 * Total Accepted:    508.5K
 * Total Submissions: 1.6M
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0
 * 且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = []
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [0]
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * -10^5 
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // 去除重复的组
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] + nums[i] > 0) {
                    do {
                        right--;
                    } while (right > 0 && nums[right] == nums[right + 1]);
                } else if (nums[left] + nums[right] + nums[i] < 0) {
                    do {
                        left++;
                    } while (left < nums.length && nums[left] == nums[left - 1]);
                } else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));
                    do {
                        left++;
                    } while (left < nums.length && nums[left] == nums[left - 1]);
                    do {
                        right--;
                    } while (right > 0 && nums[right] == nums[right + 1]);
                }
            }
        }
        return res;
    }
}
// @lc code=end
