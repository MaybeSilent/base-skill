import java.util.ArrayList;
import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 *
 * https://leetcode-cn.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (72.26%)
 * Likes:    1283
 * Dislikes: 0
 * Total Accepted:    237.6K
 * Total Submissions: 328.7K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * 
 * candidates 中的数字可以无限制重复被选取。
 * 
 * 说明：
 * 
 * 
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1：
 * 
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 * 
 * 
 * 示例 2：
 * 
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * 
 * 
 */

// @lc code=start
class Solution {
    HashMap<Integer, List<List<Integer>>> res = new HashMap<>(); // 记忆化搜索的存储结果
    {
        res.put(0, new ArrayList<>());
        res.get(0).add(new ArrayList<>());
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            List<List<Integer>> tempList = combinationSum(candidates, target - candidates[i], i);
            for (int j = 0; j < tempList.size(); j++) {
                ArrayList<Integer> addList = new ArrayList<>(tempList.get(j));
                addList.add(candidates[i]);
                // AddUniqueList(targetList, addList);
                targetList.add(addList);
            }
        }
        return targetList;
    }

    // 新增结果的时候去重
    private void AddUniqueList(List<List<Integer>> targetList, List<Integer> addList) {
        for (int i = 0; i < targetList.size(); i++) {
            if (targetList.get(i).size() != addList.size())
                continue;
            boolean sameFlag = true;
            for (int j = 0; j < addList.size(); j++) {
                if (addList.get(j) != targetList.get(i).get(j)) {
                    sameFlag = false;
                    break;
                }
            }
            if (sameFlag) {
                return;
            }
        }
        targetList.add(addList);
    }
}
// @lc code=end
