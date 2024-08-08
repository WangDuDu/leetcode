package dynamicprogramming.stringedit;

//给定两个单词 word1 和 word2 ，返回使得 word1 和 word2 相同所需的最小步数。 
//
// 每步 可以删除任意一个字符串中的一个字符。 
//
// 
//
// 示例 1： 
//
// 
//输入: word1 = "sea", word2 = "eat"
//输出: 2
//解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
// 
//
// 示例 2: 
//
// 
//输入：word1 = "leetcode", word2 = "etco"
//输出：4
// 
//
// 
//
// 提示： 
// 
//
// 
// 1 <= word1.length, word2.length <= 500 
// word1 和 word2 只包含小写英文字母 
// 
// Related Topics 字符串 动态规划 👍 420 👎 0

/**
 * 583
 *
 * 本题可以转换为求两个单词的最长公共自序列，然后求两个单词除去最长公共自序列的字符之外总共还有多少个字符
 *
 * (1)确定动态规划数组：dp[i][j]
 *      表示第一个单词的第1~i个字符和第二个单词的第1~j个字符的最长公共自序列的长度
 * (2)确定状态转移方程：
 *      word1的第i个字符和word2的第j个字符相等：dp[i][j] = dp[i - 1][j - 1] + 1
 *      word1的第i个字符和word2的第j个字符不等：dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j])
 * (3)初始化：
 *      dp[0][0] = 0, dp[0][0 ~ length2] = 0, dp[0 ~ length1][0] = 0
 * (4)
 */

public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
    }

    class Solution {
        public int minDistance(String word1, String word2) {
            int length1 = word1.length();
            int length2 = word2.length();
            int[][] dp = new int[length1 + 1][length2 + 1];
            for (int i = 1; i <= length1; i++) {
                for (int j = 1; j <= length2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Integer.max(dp[i][j - 1], dp[i - 1][j]);
                    }
                }
            }

            return length1 + length2 - 2 * dp[length1][length2];
        }
    }

}