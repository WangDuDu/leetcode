package leetcode.editor.cn;

//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 位运算 数学 字符串 模拟 👍 817 👎 0


public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {
            int length1 = a.length();
            int length2 = b.length();
            int diff0 = length1 > length2 ? length1 - length2 : length2 - length1;

            StringBuilder fill0 = new StringBuilder();
            for (int i = 0; i < diff0; i++) {
                fill0.append("0");
            }

            if (length1 < length2) {
                a = fill0 + a;
            } else {
                b = fill0 + b;
            }

            StringBuilder res = new StringBuilder();
            char carry = '0';
            for (int i = a.length() - 1; i >= 0; i--) {
                if (carry == '0') {
                    if (a.charAt(i) == '1' && b.charAt(i) == '1') {
                        res.append("0");
                        carry = '1';
                    } else if ((a.charAt(i) == '1' && b.charAt(i) == '0') || (a.charAt(i) == '0' && b.charAt(i) == '1')) {
                        res.append("1");
                        carry = '0';
                    } else {
                        res.append("0");
                        carry = '0';
                    }
                } else {
                    if (a.charAt(i) == '1' && b.charAt(i) == '1') {
                        res.append("1");
                        carry = '1';
                    } else if ((a.charAt(i) == '1' && b.charAt(i) == '0') || (a.charAt(i) == '0' && b.charAt(i) == '1')) {
                        res.append("0");
                        carry = '1';
                    } else {
                        res.append("1");
                        carry = '0';
                    }
                }
            }
            if (carry == '1') {
                res.append(1);
            }
            return res.reverse().toString();
        }

        /**
         * 遍历a和b的每一个字符，当a和b都遍历完，并且进位carry也为0的时候停止遍历
         * 遍历增加进位判断的原因是，保证最高位计算完后，如果有进位为1，还需要处理最高位和的进位
         * <p>
         * 当a的遍历到位的值为1的时候，进位carry加1，当b的遍历到位的值为1的时候，进位carry再加1，最后再加上低位的进位
         * carry = 0 代表上一步的进位，再加上a和b的当前位和为0，此时不需要进位，当前位计算后为0
         * 此时a和b的当前位都为0，进位也为0
         * carry = 1 代表上一步的进位，再加上a和b的当前位和为1，此时不需要进位，当前位计算后为1
         * 此时的情况有几种
         * a和b的当前位有一个是1一个是0，低位进位是0，
         * a和b当前位都为0，低位进位是1
         * carry = 2 代表上一步的进位，再加上a和b的当前位和为2，此时需要进位1，当前位计算后为0
         * 此时的情况有几种
         * a和b的当前位有2个是1，低位进位是0，
         * a和b的当前为有一个是1一个是0，低位进位是1
         * carry = 3 代表上一步的进位，再加上a和b的当前位和为3，此时需要进位1，当前位计算后为1
         * 此时a和b的当前位有2个是1，低位进位是1
         * 综合上述情况carry / 2的值表示进位，carry % 2 表示当前位的计算结果
         *
         * @param a
         * @param b
         * @return
         */
        public String addBinary1(String a, String b) {
            int lengthA = a.length();
            int lengthB = b.length();
            int carry = 0;

            StringBuilder str = new StringBuilder();
            while (carry == 1 || lengthA > 0 || lengthB > 0) {
                if (lengthA > 0 && a.charAt(--lengthA) == '1') {
                    carry++;
                }
                if (lengthB > 0 && b.charAt(--lengthB) == '1') {
                    carry++;
                }
                str.append(carry % 2);
                carry /= 2;
            }
            return str.reverse().toString();
        }
    }

}