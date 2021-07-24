import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=89 lang=java
 *
 * [89] 格雷编码
 *
 * https://leetcode-cn.com/problems/gray-code/description/
 *
 * algorithms
 * Medium (70.76%)
 * Likes:    312
 * Dislikes: 0
 * Total Accepted:    53K
 * Total Submissions: 74.9K
 * Testcase Example:  '2'
 *
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * 
 * 格雷编码序列必须以 0 开头。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 
 * 示例 2:
 * 
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> grayCode(int n) {
        List<String> strRes = getCode(n);
        List<Integer> res = new ArrayList<>();

        for (String str : strRes) {
            res.add(convertStrToInt(str));
        }
        return res;
    }

    public List<String> getCode(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            res.add("");
            return res;
        }

        List<String> sub = getCode(n - 1);

        int len = sub.size();
        for (int i = 0; i < len; i++) {
            res.add("0" + sub.get(i));
        }

        for (int i = len - 1; i >= 0; i--) {
            res.add("1" + sub.get(i));
        }
        return res;
    }

    public int convertStrToInt(String s) {
        char[] sChar = s.toCharArray();
        int val = 1;
        int res = 0;
        for (int i = sChar.length - 1; i >= 0; i--) {
            if (sChar[i] == '1') {
                res += val;
            }
            val *= 2;
        }
        return res;
    }
}
// @lc code=end
