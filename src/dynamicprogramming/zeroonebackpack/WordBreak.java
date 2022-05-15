package dynamicprogramming.zeroonebackpack;

//给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。 
//
// 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。 
//
// 
//
// 示例 1： 
//
// 
//输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
// 
//
// 示例 2： 
//
// 
//输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
//     注意，你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 
//输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 300 
// 1 <= wordDict.length <= 1000 
// 1 <= wordDict[i].length <= 20 
// s 和 wordDict[i] 仅有小写英文字母组成 
// wordDict 中的所有字符串 互不相同 
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 👍 1592 👎 0

/**
 * 该问题是一个完全0-1背包问题，同时是一个有顺序的背包问题，类似的硬币这样的0-1背包问题，并不关注不同取值硬币的先后顺序，只要最后的和满足一定条件即可
 * 这种问题将物品的遍历放在外边，内部循环将对当前物品的使用放在一块，例如aomount = 8 coins={3,2,1},硬币重复使用最后的和能不能为8
 * 那么遍历到3的时候dp[3] = true，dp[6] = true, dp[8] = false, 遍历到2的时候dp[8] = true, 这样实际上就是先用了两次8，再用了一次2
 * 就是把对硬币3的使用集中了起来
 * 但是本题不同，单词必须是按照一定顺序放入背包的，不嗯先用某个单词，用完之后在用下一个，这时候就要把对单词的遍历放入内层循环，i的遍历放到完成循环
 * 例如s = "applepenapple", wordDict = ["apple", "pen"]，dp[5] = true, 如果单词的遍历放到外层循环的话，
 *             for (String word : wordDict) {
 *                 for (int i = word.length(); i <= sLength; i++) {
 *                     dp[i] = (dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)) || dp[i];
 *                 }
 *             }
 * 当处理完apple的时候dp[0] ~ dp[13]只有dp[5]是true，其他都是false，下一步外层循环处理pen的时候
 * dp[0] ~ dp[13] 只有dp[8]为true，然后整个循环就结束了，最后的apple就没办法从字典中拿了。
 * 所以要将单词的循环放到内层循环，还是上边的例子
 * dp[5]将apple放入为true
 * dp[8]将pen放入为true
 * dp[13]的时候还在尝试把apple和pen分别放入，这样dp[13]为true
 *
 * (1)确定动态规划数组 dp[i] 表示到字符串的第i个字符可以利用字典中的单词拼出
 * (2)确定状态转移方程 dp[i] = (dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)) || dp[i];
 *      当前的单词放入子集：dp[i] = dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)
 *      当前单词不放入子集：dp[i] = dp[i]
 * (3)初始化
 *      dp[0]代表空字符串，我们认为不往里面放任何单词即可，所以dp[0] = true
 *
 */

import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) {

        Solution solution = new WordBreak().new Solution();
        System.out.println(solution.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(solution.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(solution.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            int sLength = s.length();
            char[] c = s.toCharArray();

            boolean[] dp = new boolean[sLength + 1];

            dp[0] = true;
            for (int i = 1; i <= sLength; i++) {
                for (String word : wordDict) {
                    if (i >= word.length()) {
                        dp[i] = (dp[i - word.length()] && s.substring(i - word.length(), i).equals(word)) || dp[i];
                    }
                }
            }


            return dp[sLength];
        }
    }

}