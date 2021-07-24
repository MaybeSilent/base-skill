import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=68 lang=java
 *
 * [68] 文本左右对齐
 *
 * https://leetcode-cn.com/problems/text-justification/description/
 *
 * algorithms
 * Hard (46.59%)
 * Likes:    140
 * Dislikes: 0
 * Total Accepted:    17.9K
 * Total Submissions: 38.3K
 * Testcase Example:  '["This", "is", "an", "example", "of", "text", "justification."]\n16'
 *
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * 
 * 说明:
 * 
 * 
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 
 * 
 * 示例:
 * 
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。       
 * ⁠    第二行同样为左对齐，这是因为这行只包含一个单词。
 * 
 * 
 * 示例 3:
 * 
 * 输入:
 * words =
 * ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science  is  what we",
 * ⁠ "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int index = 0;
        while (index < words.length) {
            // 将words中的单词全部插入
            StringBuffer lineStrbuf = new StringBuffer();
            lineStrbuf.append(words[index]);

            int lineLen = maxWidth;
            lineLen -= words[index].length();

            int count = 1;
            while (index + count < words.length && lineLen >= words[index + count].length() + 1) {
                lineLen -= (words[index + count].length() + 1);
                count++;
            }

            boolean needLeft = index + count < words.length; // 标志是否需要左对齐
            int spaceCount = count == 1 ? 1 : needLeft ? count - 1 : count;

            int[] spaceNums = new int[spaceCount];

            if (needLeft) {
                int base = lineLen / spaceCount;
                int more = lineLen % spaceCount;
                Arrays.fill(spaceNums, count == 1 ? base : base + 1);
                for (int i = 0; i < more; i++) {
                    spaceNums[i] += 1;
                }
            } else {
                Arrays.fill(spaceNums, 1);
                spaceNums[spaceCount - 1] = lineLen;
            }

            for (int i = 0; i < spaceCount; i++) {
                for (int j = 0; j < spaceNums[i]; j++) {
                    lineStrbuf.append(' ');
                }
                if (i + 1 < count) {
                    lineStrbuf.append(words[index + i + 1]);
                }
            }
            index += count;
            res.add(lineStrbuf.toString());
        }
        return res;
    }
}
// @lc code=end
