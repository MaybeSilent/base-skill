import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/description/
 *
 * algorithms
 * Medium (64.16%)
 * Likes:    933
 * Dislikes: 0
 * Total Accepted:    343.1K
 * Total Submissions: 534.7K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 
 * 
 * 
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 返回其层序遍历结果：
 * 
 * 
 * [
 * ⁠ [3],
 * ⁠ [9,20],
 * ⁠ [15,7]
 * ]
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 通过遍历list的形式
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<TreeNode> nodeTree = new ArrayList<TreeNode>();
        if (root != null) {
            nodeTree.add(root);
        }
    
        // 判断nodeTree是否非空
        while (nodeTree.size() != 0) {
            List<TreeNode> nextNodeTree = new ArrayList<>();
            List<Integer> nodeRes = new ArrayList<>();
            for (int i = 0; i < nodeTree.size(); i++) {
                nodeRes.add(nodeTree.get(i).val);
                if (nodeTree.get(i).left != null) {
                    nextNodeTree.add(nodeTree.get(i).left);
                }

                if (nodeTree.get(i).right != null) {
                    nextNodeTree.add(nodeTree.get(i).right);
                }
            }
            nodeTree = nextNodeTree;
            res.add(nodeRes);
        }

        return res;
    }
}
// @lc code=end
