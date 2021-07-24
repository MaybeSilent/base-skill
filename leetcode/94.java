import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Easy (75.48%)
 * Likes:    1025
 * Dislikes: 0
 * Total Accepted:    479.7K
 * Total Submissions: 635.4K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = []
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：root = [1]
 * 输出：[1]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目在范围 [0, 100] 内
 * -100 
 * 
 * 
 * 
 * 
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
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
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return res;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);

        while (stack.size() > 0) {
            TreeNode curNode = stack.getLast();
            while (curNode.left != null) {
                // System.out.println(">>>>>");
                stack.add(curNode.left);
                curNode = curNode.left;
            }

            // System.out.println("!!!");
            TreeNode lastNode = stack.removeLast();
            while (lastNode != null) {
                // System.out.println(lastNode.val);
                res.add(lastNode.val);
                if (lastNode.right != null) {
                    // System.out.println("<<<<<");
                    stack.add(lastNode.right);
                    break;
                }
                if (stack.size() > 0) {
                    lastNode = stack.removeLast();
                } else {
                    lastNode = null;
                }

            }

        }

        return res;
    }

    public List<Integer> inorderTraversalRecursive(TreeNode root) {
        if (root == null) {
            return res;
        }
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }
}
// @lc code=end
