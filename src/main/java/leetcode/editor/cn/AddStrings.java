package leetcode.editor.cn;

//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。 
//
// 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。 
//
// 
//
// 示例 1： 
//
// 
//输入：num1 = "11", num2 = "123"
//输出："134"
// 
//
// 示例 2： 
//
// 
//输入：num1 = "456", num2 = "77"
//输出："533"
// 
//
// 示例 3： 
//
// 
//输入：num1 = "0", num2 = "0"
//输出："0"
// 
//
// 
//
// 
//
// 提示： 
//
// 
// 1 <= num1.length, num2.length <= 10⁴ 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 
// Related Topics 数学 字符串 模拟 👍 566 👎 0


public class AddStrings {
    public static void main(String[] args) {
        Solution solution = new AddStrings().new Solution();
    }

    class Solution {
        /**
         * 遍历num1和num2的每一个字符，当num1和num2都遍历完，并且进位carry也为0的时候停止遍历
         * 遍历增加进位判断的原因是，保证最高位计算完后，如果有进位为1，还需要处理最高位和的进位
         * <p>
         * 设num1的当前位为a， num2的当前位为b
         * 那么a + b对10取余是当前位的计算记过，a + b除以10取商是进位
         *
         * @param num1
         * @param num2
         * @return
         */
        public String addStrings(String num1, String num2) {
            int length1 = num1.length();
            int length2 = num2.length();
            int carry = 0;

            StringBuilder res = new StringBuilder();
            while (carry == 1 || length1 > 0 || length2 > 0) {
                int a = 0;
                if (length1 > 0) {
                    a = num1.charAt(--length1) - '0';
                }
                int b = 0;
                if (length2 > 0) {
                    b = num2.charAt(--length2) - '0';
                }
                res.append((a + b + carry) % 10);
                carry = (a + b + carry) / 10;
            }

            return res.reverse().toString();
        }
    }

}