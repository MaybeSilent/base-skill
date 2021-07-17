
/*
 * @lc app=leetcode.cn id=97 lang=java
 *
 * [97] 交错字符串
 *
 * https://leetcode-cn.com/problems/interleaving-string/description/
 *
 * algorithms
 * Medium (45.77%)
 * Likes:    491
 * Dislikes: 0
 * Total Accepted:    53K
 * Total Submissions: 115.8K
 * Testcase Example:  '"aabcc"\n"dbbca"\n"aadbbcbcac"'
 *
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * 
 * 
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| 
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 +
 * ...
 * 
 * 
 * 提示：a + b 意味着字符串 a 和 b 连接。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * 0 
 * s1、s2、和 s3 都由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        char[] s1Str = s1.toCharArray();
        char[] s2Str = s2.toCharArray();
        char[] s3Str = s3.toCharArray();

        boolean[][] dp = new boolean[s1Str.length + 1][s2Str.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1Str.length; i++) {
            dp[i][0] = dp[i - 1][0] && s1Str[i - 1] == s3Str[i - 1];
        }

        for (int i = 1; i <= s2Str.length; i++) {
            dp[0][i] = dp[0][i - 1] && s2Str[i - 1] == s3Str[i - 1];
        }

        for (int i = 1; i <= s1Str.length; i++) {
            for (int j = 1; j <= s2Str.length; j++) {
                dp[i][j] = (dp[i - 1][j] && s1Str[i - 1] == s3Str[i + j - 1])
                        || (dp[i][j - 1] && s2Str[j - 1] == s3Str[i + j - 1]);
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public boolean TLE(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int[][] results = new int[s1.length() + 1][s2.length() + 1];
        return isInterleaveChar(results, s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
    }

    public boolean isInterleaveChar(int[][] results, char[] s1, char[] s2, char[] s3, int index1, int index2,
            int index3) {
        if (index3 >= s3.length) {
            results[index1][index2] = 1;
            return true;
        }

        if (results[index1][index2] != 0) {
            return results[index1][index2] == 1;
        }

        while (index3 < s3.length) {
            if (index1 >= s1.length || index2 >= s2.length) {
                if (index1 >= s1.length) {
                    for (int i = 0; i < s2.length - index2; i++) {
                        if (s2[i + index2] != s3[i + index3]) {
                            results[index1][index2] = -1;
                            return false;
                        }
                    }
                    results[index1][index2] = 1;
                    return true;
                }

                if (index2 >= s2.length) {
                    for (int i = 0; i < s1.length - index1; i++) {
                        if (s1[i + index1] != s3[i + index3]) {
                            results[index1][index2] = -1;
                            return false;
                        }
                    }
                    results[index1][index2] = 1;
                    return true;
                }
            }

            if (s1[index1] == s3[index3] && s2[index2] == s3[index3]) {
                return isInterleaveChar(results, s1, s2, s3, index1 + 1, index2, index3 + 1)
                        || isInterleaveChar(results, s1, s2, s3, index1, index2 + 1, index3 + 1);
            } else if (s1[index1] == s3[index3]) {
                index1++;
                index3++;
            } else if (s2[index2] == s3[index3]) {
                index2++;
                index3++;
            } else {
                results[index1][index2] = -1;
                return false;
            }
        }
        results[index1][index2] = 1;
        return true;
    }
}
// @lc code=end
