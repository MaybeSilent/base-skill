/*
 * @lc app=leetcode.cn id=87 lang=java
 *
 * [87] 扰乱字符串
 *
 * https://leetcode-cn.com/problems/scramble-string/description/
 *
 * algorithms
 * Hard (49.12%)
 * Likes:    386
 * Dislikes: 0
 * Total Accepted:    39.5K
 * Total Submissions: 80.5K
 * Testcase Example:  '"great"\n"rgeat"'
 *
 * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
 * 
 * 如果字符串的长度为 1 ，算法停止
 * 如果字符串的长度 > 1 ，执行下述步骤：
 * 
 * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y
 * 。
 * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y +
 * x 。
 * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
 * 
 * 
 * 
 * 
 * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s1 = "great", s2 = "rgeat"
 * 输出：true
 * 解释：s1 上可能发生的一种情形是：
 * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
 * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
 * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
 * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
 * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
 * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
 * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s1 = "abcde", s2 = "caebd"
 * 输出：false
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s1 = "a", s2 = "a"
 * 输出：true
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * s1.length == s2.length
 * 1 
 * s1 和 s2 由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isScramble(String s1, String s2) {
        // 字符串开头的字母应该与其余字母互相对应
        if (s1.length() != s2.length()) {
            return false;
        }
        int[][][] resMap = new int[s1.length()][s1.length()][s1.length() + 1];
        return isLegal(resMap, s1.toCharArray(), s2.toCharArray(), 0, 0, s1.length());
    }

    // 递归查询并比较字符数组是否一致
    private boolean isLegal(int[][][] resMap, char[] s1, char[] s2, int start1, int start2, int len) {

        if (resMap[start1][start2][len] != 0) {
            return resMap[start1][start2][len] == 1;
        }

        if (len <= 0) {
            resMap[start1][start2][len] = 1;
            return true;
        }

        if (len == 1) {
            resMap[start1][start2][len] = s1[start1] == s2[start2] ? 1 : -1;
            return s1[start1] == s2[start2];
        }
        // 校验两个子字符串
        int[] value = new int[256];
        for (int i = 0; i < len; i++) {
            value[s1[start1 + i]]++;
        }
        for (int i = 0; i < len; i++) {
            value[s2[start2 + i]]--;
            if (value[s2[start2 + i]] < 0) {
                resMap[start1][start2][len] = -1;
                return false;
            }
        }

        for (int i = 1; i < len; i++) {

            if (isLegal(resMap, s1, s2, start1, start2, i)
                    && isLegal(resMap, s1, s2, start1 + i, start2 + i, len - i)) {
                resMap[start1][start2][len] = 1;
                return true;
            }

            if (isLegal(resMap, s1, s2, start1, start2 + len - i, i)
                    && isLegal(resMap, s1, s2, start1 + i, start2, len - i)) {
                resMap[start1][start2][len] = 1;
                return true;
            }
        }
        resMap[start1][start2][len] = -1;
        return false;
    }

}
// @lc code=end
