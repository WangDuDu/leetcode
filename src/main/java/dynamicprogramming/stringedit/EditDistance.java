package dynamicprogramming.stringedit;

//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 👍 2377 👎 0

/**
 * 72
 *
 * (1)确定动态规划数组
 *      dp[i][j]表示取word1的1~i个字符变成word2的1~j个字符所需要的最小步数
 * (2)确定状态转移方程
 *      当word1的第i个字符等于word2的第j个字符的时候，在这一步不需要任何的变化(改变，增加，删除字符)，
 *      所以取word1的1~i个字符变成word2的1~j个字符所需要的最小步数等于取word1的1~i-1个字符变成word2的1~j-1个字符所需要的最小步数，即
 *      dp[i][j] = dp[i - 1][j - 1]
 *      当word1的第i个字符不等于word2的第j个字符的时候，在这一步需要进行变化，变化有三种改变、增加和删除，三种不同改变方式对应的状态转移方程如下所示
 *      将word1的第i个字符变为word2的第j个字符：dp[i][j] = dp[i - 1][j - 1] + 1 (将word2的第j个字符变为word1的第i个字符)
 *      将word1的第i个字符删除：dp[i][j] = dp[i - 1][j] + 1 (等效于word2添加一个和i相同的字符)
 *      将word2的第j个字符删除：dp[i][j] = dp[i][j - 1] + 1 (等效于word1添加一个和j相同的字符)
 *      取上述三种情况的最小值，综合如下所示
 *      dp[i][j] = Integer.min(dp[i - 1][j - 1], Integer.min(dp[i - 1][j], dp[i][j - 1])) + 1
 * (3)确定初始值
 *      dp[0][0 ~ j] 表示word2变成空字符串需要几步，也就是删除掉j个元素，需要j步
 *      dp[0 ~ i][0] 表示word1变成空字符串需要几步，也就是删除掉i个元素，需要i步
 */
public class EditDistance{
    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
    }

    class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            int[][] dp = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }

            for (int j = 0; j <= n; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Integer.min(dp[i - 1][j - 1], Integer.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }

            return dp[m][n];
        }
    }

}