/*
 * @lc app=leetcode.cn id=72 lang=java
 *
 * [72] 编辑距离
 *
 * https://leetcode-cn.com/problems/edit-distance/description/
 *
 * algorithms
 * Hard (60.92%)
 * Likes:    1679
 * Dislikes: 0
 * Total Accepted:    146.5K
 * Total Submissions: 240.5K
 * Testcase Example:  '"horse"\n"ros"'
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 
 * 你可以对一个单词进行如下三种操作：
 * 
 * 
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * word1 和 word2 由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        // 动态规划相关的单词编辑总数
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        char[] word1Str = word1.toCharArray();
        char[] word2Str = word2.toCharArray();
        // word1需要删除字母
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        // word1需要增加字母
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            // 将word1转化为word2
            for (int j = 1; j <= word2.length(); j++) {
                if (word1Str[i - 1] == word2Str[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // dp[i-1][j] 删除word1
                    // dp[i-1][j-1] 修改word1
                    // dp[i][j-1] 增加word1
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                }
            }
        }

        // for (int i = 0; i <= word1.length(); i++) {
        // for (int j = 0; j <= word2.length(); j++) {
        // System.out.println(dp[i][j]);
        // }
        // System.out.println();
        // }

        return dp[word1.length()][word2.length()];
    }
}
// @lc code=end
