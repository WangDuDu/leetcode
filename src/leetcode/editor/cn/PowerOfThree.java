package leetcode.editor.cn;

//给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3ˣ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 27
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 9
//输出：true
// 
//
// 示例 4： 
//
// 
//输入：n = 45
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= n <= 2³¹ - 1 
// 
//
// 
//
// 进阶：你能不使用循环或者递归来完成本题吗？ 
// Related Topics 递归 数学 👍 255 👎 0


public class PowerOfThree {
    public static void main(String[] args) {
        Solution solution = new PowerOfThree().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPowerOfThree(int n) {
            if (n <= 0) {
                return false;
            }
            if (n == 3 || n == 1) {
                return true;
            }
            if (n % 3 == 0) {
                return isPowerOfThree(n / 3);
            }
            return false;
        }

        /**
         * 取值范围内最大的3的幂次方是1162261467，如果n是3的幂次方，那么n % 3 == 0。证明方法就是分解因式，1162261467可以分解为19个3相乘
         * 那么如果n能被1162261467整除，那么n一定能够分解成若干个2相乘，所以n一定是3的幂次方
         *
         * @param n
         * @return
         */
        public boolean isPowerOfThree1(int n) {
            return n > 0 && (1162261467 % n == 0);
        }

        /**
         * 利用换底公式，如果n是3的幂次方，那么log3(n) = 正整数，根据换底公式log3(n) = log10(n) / log10(3) = 正整数
         * 如何判断log10(n) / log10(3) = 正整数
         * 可以将log10(n) / log10(3)转为int类型，如果转换成int类型和log10(n) / log10(3)本身的结果相同，那么证明log10(n) / log10(3) = 正整数
         *
         * @param n
         * @return
         */
        public boolean isPowerOfThree2(int n) {
            return (n > 0 && (int) (Math.log10(n) / Math.log10(3)) - (Math.log10(n) / Math.log10(3)) == 0);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}