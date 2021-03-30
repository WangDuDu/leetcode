package doublepoint;

import java.util.List;

/**
 * Created by wangshuyang on 2021-3-15.
 *
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 示例 1:
 *
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * 输出:
 * "apple"
 * 示例 2:
 *
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * 输出:
 * "a"
 * 说明:
 *
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestWords {

    public String findLongestWord(String s, List<String> dictionary) {
        String longestWord = "";
        for (String target : dictionary) {
            Integer targetWordLength = target.length();
            Integer currentLogestWordLength = longestWord.length();
            if (targetWordLength < currentLogestWordLength
                    || (currentLogestWordLength.equals(targetWordLength) && longestWord.compareTo(target) < 0)) {
                continue;
            }

            if (isSubString(s, target)) {
                longestWord = target;
            }
        }

        return longestWord;
    }

    public Boolean isSubString(String s, String target) {
        int i = 0, j = 0;

        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) {
                j++;
            }
            i++;
        }

        return j == target.length();
    }
}
