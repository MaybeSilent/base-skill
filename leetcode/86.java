/*
 * @lc app=leetcode.cn id=86 lang=java
 *
 * [86] 分隔链表
 *
 * https://leetcode-cn.com/problems/partition-list/description/
 *
 * algorithms
 * Medium (62.97%)
 * Likes:    422
 * Dislikes: 0
 * Total Accepted:    108.9K
 * Total Submissions: 172.9K
 * Testcase Example:  '[1,4,3,2,5,2]\n3'
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 
 * -200 
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
    public ListNode partition(ListNode head, int x) {
        ListNode res = new ListNode();
        res.next = head;

        // prev前置相关节点
        ListNode prev = res;
        while (prev.next != null && prev.next.val < x) {
            prev = prev.next;
        }

        // 小于的节点全部插入到prev中
        ListNode curNode = prev.next;
        while (curNode != null && curNode.next != null) {
            if (curNode.next.val < x) {
                // 进行节点的移动
                ListNode moveNode = curNode.next;
                curNode.next = moveNode.next;
                moveNode.next = prev.next;
                prev.next = moveNode;
                prev = moveNode;
            } else {
                curNode = curNode.next;
            }
        }

        return res.next;
    }
}
// @lc code=end
