import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (79.92%)
 * Likes:    1235
 * Dislikes: 0
 * Total Accepted:    268.5K
 * Total Submissions: 335.9K
 * Testcase Example:  '[1,2,3]'
 *
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -10 
 * nums 中的所有元素 互不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, 0, temp, i);
        }
        return res;
    }

    private void dfs(int[] nums, int index, ArrayList<Integer> list, int k) {
        if (list.size() == k) {
            res.add((List<Integer>) list.clone());
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, list, k);
            list.remove(list.size() - 1);
        }
    }
}
// @lc code=end
