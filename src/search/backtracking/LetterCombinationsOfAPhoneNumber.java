package search.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuyang on 2021-8-6.
 *
 * Backtracking（回溯）属于 DFS。
 *
 * 普通 DFS 主要用在 可达性问题 ，这种问题只需要执行到特点的位置然后返回即可。
 * 而 Backtracking 主要用于求解 排列组合 问题，例如有 { 'a','b','c' } 三个字符，求解所有由这三个字符排列得到的字符串，这种问题在执行到特定的位置返回之后还会继续执行求解过程。
 * 因为 Backtracking 不是立即返回，而要继续求解，因此在程序实现时，需要注意对元素的标记问题：
 *
 * 在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
 * 但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
 */
public class LetterCombinationsOfAPhoneNumber {
    // 初始化一个不可变数组，数组中每个元素的下标就是该字符串所在键盘的数字
    private static final String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (null == digits || digits.length() == 0) {
            return combinations;
        }
        doCombination(new StringBuilder(), combinations, digits);
        return combinations;
    }

    // DFS
    private void doCombination(StringBuilder prefix, List<String> combinations, String digits) {
        if (prefix.length() == digits.length()) {
            combinations.add(prefix.toString());
        }

        int number = digits.toCharArray()[prefix.length()] - '0';
        // 定位到当前应该访问哪个数字
        char[] charsOfNumber = KEYS[number].toCharArray();
        // 遍历当前数字所有可能的字母
        for (char c : charsOfNumber) {
            // 将当前char加入到prefix中，这一步代表当前char访问过了，做一个记录
            prefix.append(c);
            doCombination(prefix, combinations, digits);
            // 完成递归后将当前这个char删掉。循环的下一次重新填充字符串的该位置
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
