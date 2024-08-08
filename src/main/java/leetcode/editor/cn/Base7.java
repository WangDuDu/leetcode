package leetcode.editor.cn;

//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: num = -7
//输出: "-10"
// 
//
// 
//
// 提示： 
//
// 
// -10⁷ <= num <= 10⁷ 
// 
// Related Topics 数学 👍 179 👎 0



public class Base7 {
    public static void main(String[] args) {
        Solution solution = new Base7().new Solution();
    }

    class Solution {
        public String convertToBase7(int num) {
            if (num == 0) {
                return "0";
            }
            boolean isNegative = false;
            if (num < 0) {
                num = -num;
                isNegative = true;
            }

            StringBuilder stringBuilder = new StringBuilder();
            while (num != 0) {
                stringBuilder.append(num % 7);
                num = num / 7;
            }
            if (isNegative) {
                stringBuilder.append('-');
            }
            return stringBuilder.reverse().toString();
        }
    }
}