import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*
 * @lc app=leetcode.cn id=18 lang=java
 *
 * [18] 四数之和
 *
 * https://leetcode-cn.com/problems/4sum/description/
 *
 * algorithms
 * Medium (40.28%)
 * Likes:    851
 * Dislikes: 0
 * Total Accepted:    180.5K
 * Total Submissions: 447.4K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：答案中不可以包含重复的四元组。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [], target = 0
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * -10^9 
 * -10^9 
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        HashSet<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                numSet.add(nums[i] + nums[j]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;
                int leftTarget = target - nums[i] - nums[j];
                if (!numSet.contains(leftTarget))
                    continue;
                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    int subRes = leftTarget - nums[left] - nums[right];
                    if (subRes > 0) {
                        left++;
                    } else if (subRes < 0) {
                        right--;
                    } else {
                        List<Integer> last = res.size() == 0 ? null : res.get(res.size() - 1);
                        if (last == null || last.get(0) != nums[i] || last.get(1) != nums[j]
                                || last.get(2) != nums[left] || last.get(3) != nums[right]) {
                            res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        }
                        left++;
                        right--;
                    }
                }
            }
        }

        return res;
    }
}
// @lc code=end
