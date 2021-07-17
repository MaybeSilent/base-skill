import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=93 lang=java
 *
 * [93] 复原 IP 地址
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (53.19%)
 * Likes:    618
 * Dislikes: 0
 * Total Accepted:    131K
 * Total Submissions: 245.9K
 * Testcase Example:  '"25525511135"'
 *
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
 * 和 "192.168@1.1" 是 无效 IP 地址。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * s 仅由数字组成
 * 
 * 
 */

// @lc code=start
class Solution {
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) {
            return res;
        }
        dfs(s.toCharArray(), new StringBuffer(), 0, 0);
        return res;
    }

    public void dfs(char[] sStr, StringBuffer sb, int index, int count) {
        if (count == 3) {
            int len = sStr.length - index;
            if (isValidSub(sStr, index, len)) {
                int start = sb.length();
                sb.append(sStr, index, len);
                res.add(sb.toString());
                sb.delete(start, start + len);
            }
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (isValidSub(sStr, index, i)) {
                int start = sb.length();
                sb.append(sStr, index, i);
                sb.append('.');
                dfs(sStr, sb, index + i, count + 1);
                sb.delete(start, start + i + 1);
            }
        }
    }

    public boolean isValidSub(char[] sStr, int index, int len) {
        if (index + len > sStr.length) {
            return false;
        }
        if (len == 1) {
            return sStr[index] >= '0' && sStr[index] <= '9';
        } else if (len == 2) {
            return sStr[index] >= '1' && sStr[index] <= '9' && sStr[index + 1] >= '0' && sStr[index + 1] <= '9';
        } else if (len == 3) {
            return (sStr[index] == '1' && sStr[index + 1] >= '0' && sStr[index + 1] <= '9' && sStr[index + 2] >= '0'
                    && sStr[index + 2] <= '9')
                    || (sStr[index] == '2' && ((sStr[index + 1] >= '0' && sStr[index + 1] <= '4'
                            && sStr[index + 2] >= '0' && sStr[index + 2] <= '9')
                            || (sStr[index + 1] == '5' && sStr[index + 2] >= '0' && sStr[index + 2] <= '5')));
        }
        return false;
    }
}
// @lc code=end
