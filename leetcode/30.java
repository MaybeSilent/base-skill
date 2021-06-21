import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=30 lang=java
 *
 * [30] 串联所有单词的子串
 *
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (34.65%)
 * Likes:    478
 * Dislikes: 0
 * Total Accepted:    65.7K
 * Total Submissions: 189.4K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 由小写英文字母组成
 * 1 
 * 1 
 * words[i] 由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();

        int min = 0;
        for (int i = 0; i < words.length; i++) {
            min += words[i].length();
        }
        Arrays.sort(words);
        for (int i = 0; i <= s.length() - min; i++) {
            boolean[] used = new boolean[words.length];
            if (dfs(s, i, words, used, 0)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean dfs(String s, int index, String[] words, boolean[] used, int usedCount) {
        if (usedCount == used.length)
            return true;
        if (index >= s.length())
            return false;
        for (int i = 0; i < words.length; i++) {
            if (!used[i] && equal(s, index, words[i])) {
                used[i] = true;
                boolean res = dfs(s, index + words[i].length(), words, used, usedCount + 1);
                if (res)
                    return true;
                used[i] = false;
                while (i + 1 < words.length && words[i].equals(words[i + 1]))
                    i++;
            }
        }
        return false;
    }

    public boolean equal(String s, int index, String word) {
        return s.startsWith(word, index);
    }
}
// @lc code=end
