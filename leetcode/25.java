/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (64.90%)
 * Likes:    1102
 * Dislikes: 0
 * Total Accepted:    175.1K
 * Total Submissions: 269.7K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 
 * 进阶：
 * 
 * 
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 列表中节点的数量在范围 sz 内
 * 1 
 * 0 
 * 1 
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }
        ListNode res = new ListNode();
        res.next = head;
        ListNode temp = res;
        while (temp != null && temp.next != null) {
            ListNode[] nodes = reverse(temp.next, k);
            temp.next = nodes[1];
            temp = nodes[0];
        }
        return res.next;
    }

    public ListNode[] reverse(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 1; i < k; i++) {
            temp = temp.next;
            if (temp == null) {
                return new ListNode[] { null, head };
            }
        }
        ListNode prev = temp.next;
        ListNode cur = head;
        for (int i = 0; i < k; i++) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return new ListNode[] { head, prev };
    }
}
// @lc code=end
