/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (39.91%)
 * Likes:    3951
 * Dislikes: 0
 * Total Accepted:    381.2K
 * Total Submissions: 955.1K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * ·
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000·
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * nums1.length == m
 * nums2.length == n
 * 0 
 * 0 
 * 1 
 * -10^6 
 * 
 * 
 * 
 * 
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * 
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        return (l1 + l2) % 2 != 0 ? (double)getkthNum(nums1, nums2, (l1 + l2) / 2) : 
                (double)(getkthNum(nums1, nums2, (l1 + l2) / 2 - 1) + getkthNum(nums1, nums2, (l1 + l2) / 2)) / 2;
    }


    private int getkthNum(int[] nums1, int[] nums2, int k) {
        int left1 = 0, right1 = nums1.length - 1, left2 = 0, right2 = nums2.length - 1;
        
        while (true) {
            if (left1 == nums1.length) return nums2[left2 + k];
            if (left2 == nums2.length) return nums1[left1 + k];
            if (k == 0) return Math.min(nums1[left1], nums2[left2]);
            int add = k % 2 == 0 ? k / 2 - 1 : k / 2 ;
            int mid1 = Math.min(left1 + add, right1), mid2 = Math.min(left2 + add, right2);
            if (nums1[mid1] >= nums2[mid2]) {
                k -= (mid2 - left2 + 1);
                left2 = mid2 + 1;
            } else {
                k -= (mid1 - left1 + 1);
                left1 = mid1 + 1;
            }
        }
    }

}
// @lc code=end

