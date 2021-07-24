import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=95 lang=java
 *
 * [95] 不同的二叉搜索树 II
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/description/
 *
 * algorithms
 * Medium (69.26%)
 * Likes:    929
 * Dislikes: 0
 * Total Accepted:    90.6K
 * Total Submissions: 130.6K
 * Testcase Example:  '3'
 *
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：[[1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 
 * 
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTreeOfValue(1, n);
    }

    public List<TreeNode> generateTreeOfValue(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start >= end) {
            if (start == end) {
                res.add(new TreeNode(start));
            } else {
                res.add(null);
            }
            return res;
        }

        for (int i = start; i <= end; i++) {

            List<TreeNode> leftTree = generateTreeOfValue(start, i - 1);
            List<TreeNode> rightTree = generateTreeOfValue(i + 1, end);
            for (int j = 0; j < leftTree.size(); j++) {
                for (int k = 0; k < rightTree.size(); k++) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftTree.get(j);
                    node.right = rightTree.get(k);
                    res.add(node);
                }
            }
        }
        return res;
    }
}
// @lc code=end
