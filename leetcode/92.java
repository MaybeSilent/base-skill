/*
 * @lc app=leetcode.cn id=92 lang=java
 *
 * [92] 反转链表 II
 *
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (54.34%)
 * Likes:    951
 * Dislikes: 0
 * Total Accepted:    181.8K
 * Total Submissions: 333.9K
 * Testcase Example:  '[1,2,3,4,5]\n2\n4'
 *
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left  。请你反转从位置 left 到位置 right 的链表节点，返回
 * 反转后的链表 。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中节点数目为 n
 * 1 
 * -500 
 * 1 
 * 
 * 
 * 
 * 
 * 进阶： 你可以使用一趟扫描完成反转吗？
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode prev = new ListNode();
        prev.next = head;

        ListNode resNode = prev;
        int index = 0;
        while (index != left - 1) {
            index++;
            prev = prev.next;
        }

        ListNode begin = prev;

        prev = prev.next;
        index++;
        ListNode curNode = prev.next;
        while (index < right) {
            ListNode nextNode = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = nextNode;
            index++;
        }

        ListNode start = begin.next;
        start.next = curNode;
        begin.next = prev;

        return resNode.next;
    }
}
// @lc code=end
