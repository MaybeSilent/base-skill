import java.util.ArrayList;
import java.util.List;
/*
 * @lc app=leetcode.cn id=103 lang=java
 *
 * [103] 二叉树的锯齿形层序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (57.13%)
 * Likes:    472
 * Dislikes: 0
 * Total Accepted:    152.5K
 * Total Submissions: 266.9K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 返回锯齿形层序遍历如下：
 * 
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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

        // 进行正向与逆向的加减
        boolean reverse = false;
        for (int i = 0; i < res.size(); i++) {
            if (reverse) {
                int length = res.get(i).size() / 2;
                for (int j = 0; j < length; j++) {
                    int tmp = res.get(i).get(j);
                    res.get(i).set(j, res.get(i).get(res.get(i).size() - j - 1));
                    res.get(i).set(res.get(i).size() - j - 1, tmp);
                }
            }
            reverse = !reverse;
        }

        return res;
    }
}
// @lc code=end
