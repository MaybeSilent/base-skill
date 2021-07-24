import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=60 lang=java
 *
 * [60] 排列序列
 *
 * https://leetcode-cn.com/problems/permutation-sequence/description/
 *
 * algorithms
 * Hard (52.21%)
 * Likes:    540
 * Dislikes: 0
 * Total Accepted:    83.7K
 * Total Submissions: 160.4K
 * Testcase Example:  '3\n3'
 *
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * 
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * 
 * 给定 n 和 k，返回第 k 个排列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3, k = 3
 * 输出："213"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 4, k = 9
 * 输出："2314"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：n = 3, k = 1
 * 输出："123"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public String getPermutation(int n, int k) {
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            res[i] = (char) (i + 1 + '0');
        }

        int idx = 0;
        while (k > 1) {
            int sum = fac(n - idx - 1);
            int i = 0;
            while (k > sum) {
                k -= sum;
                i++;
            }

            swap(res, idx, idx + i);
            idx++;

            Arrays.sort(res, idx, res.length);
        }

        return new String(res);
    }

    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    private int fac(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }
}
// @lc code=end
