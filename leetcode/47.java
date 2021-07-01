import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (63.20%)
 * Likes:    737
 * Dislikes: 0
 * Total Accepted:    179.6K
 * Total Submissions: 283.6K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * ⁠[1,2,1],
 * ⁠[2,1,1]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -10 
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        dfs(nums, new boolean[nums.length], new LinkedList<>());
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, LinkedList<Integer> list) {
        if (list.size() == visited.length) {
            res.add((List<Integer>) list.clone());
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                    continue;

                visited[i] = true;
                list.add(nums[i]);
                dfs(nums, visited, list);
                list.removeLast();
                visited[i] = false;
            }
        }
    }
}

// @lc code=end
