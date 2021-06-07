import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=31 lang=java
 *
 * [31] 下一个排列
 *
 * https://leetcode-cn.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (36.90%)
 * Likes:    1136
 * Dislikes: 0
 * Total Accepted:    168K
 * Total Submissions: 455K
 * Testcase Example:  '[1,2,3]'
 *
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 必须 原地 修改，只允许使用额外常数空间。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：nums = [1]
 * 输出：[1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    public void nextPermutation(int[] nums) {
        int index = getReverseIncreaseIndex(nums);
        if (index < 0) {
            reverseNums(nums);
            return;
        }

        for (int i = nums.length - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                Arrays.sort(nums, index + 1, nums.length);
                return;
            }
        }
    }

    int getReverseIncreaseIndex(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                return i - 1;
            }
        }
        return -1;
    }

    void reverseNums(int[] nums) {
        int len = nums.length / 2;
        int temp = 0;
        for (int i = 0; i < len; i++) {
            int tail = nums.length - 1 - i;
            temp = nums[i];
            nums[i] = nums[tail];
            nums[tail] = temp;
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
// @lc code=end
