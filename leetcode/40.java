import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (63.63%)
 * Likes:    601
 * Dislikes: 0
 * Total Accepted:    164K
 * Total Submissions: 257.8K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 
 * 说明：
 * 
 * 
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1:
 * 
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 
 */

// @lc code=start
class Solution {
    HashMap<Integer, List<List<Integer>>> res = new HashMap<>(); // 记忆化搜索的存储结果
    {
        res.put(0, new ArrayList<>());
        res.get(0).add(new ArrayList<>());
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates, target, 0);
    }

    private List<List<Integer>> combinationSum(int[] candidates, int target, int index) {
        if (res.containsKey(target)) {
            return res.get(target);
        }

        List<List<Integer>> targetList = new ArrayList<>(); // 结果为target的数组值
        if (target < 0) {
            return targetList;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;

            List<List<Integer>> tempList = combinationSum(candidates, target - candidates[i], i + 1);
            for (int j = 0; j < tempList.size(); j++) {
                ArrayList<Integer> addList = new ArrayList<>(tempList.get(j));
                addList.add(candidates[i]);
                // AddUniqueList(targetList, addList);
                targetList.add(addList);
            }
        }
        return targetList;
    }
}
// @lc code=end
