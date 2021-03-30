package doublepoint;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 *  
 *
 * 示例 1：
 *
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 *
 * 输入："leetcode"
 * 输出："leotcede"
 *  
 *
 * 提示：
 *
 * 元音字母不包含字母 "y" 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Created by wangshuyang on 2020-9-22.
 */
public class ReverseVowels {

    private final static HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));


    public String reverseVowels(String s) {
        char[] arrays = s.toCharArray();

        int left = 0;
        int right = arrays.length - 1;
        while (left < right) {
            if (vowels.contains(arrays[left])) {
                if (vowels.contains(arrays[right])) {
                    char temp = arrays[left];
                    arrays[left] = arrays[right];
                    arrays[right] = temp;
                    left++;
                    right--;
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }
        return String.copyValueOf(arrays);
    }
}
