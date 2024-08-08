package leetcode.editor.cn;

//给定一个整数 n ，返回 n! 结果中尾随零的数量。 
//
// 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：0
//解释：3! = 6 ，不含尾随 0
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：1
//解释：5! = 120 ，有一个尾随 0
// 
//
// 示例 3： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 10⁴ 
// 
//
// 
//
// 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？ 
// Related Topics 数学 👍 680 👎 0


public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution solution = new FactorialTrailingZeroes().new Solution();
    }

    class Solution {
        /**
         * 5的倍数     5、10、15...分解质因数有至少1个5
         * 25的倍数    25、50、75...分解质因数有至少2个个5
         * 125的倍数   125、250、375...分解质因数有至少3个5
         * 以此类推5的i次的倍数至少有i个5，同时25的倍数的两个5已经包含了5的倍数的1个5，所以总的5的数目可以表示为
         * n / 5 + n / (5 * 5) + n / (5 * 5 * 5) + ... + 0
         *
         * n / 5 表示[1,n]内 5的倍数的数目
         * n / (5 * 5) 表示[1,n]内 25的倍数的数目，以此类推
         * @param n
         * @return
         */
        public int trailingZeroes(int n) {
            return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
        }

        /**
         * n! = n * (n - 1) * (n - 2) * ... * 1
         * 将每一个乘数都进行质因数分解，例如
         * 6！= (2 * 3) * 5 * (2 * 2) * 3 * 2 * 1
         * 每一对2和5的承继为10，就会在阶乘结果的末尾多一个0，能被2整除的数要多于能被5整除的数，所以2和5组合的数量取决于5的个数
         * 所以我们要求每个乘数分解质因数后5的总数
         *
         * 这里遍历乘数的时候只遍历5的倍数即可，非5的倍数不会分解质因数得到5
         *
         * 这种接法不是对数级别的，但是因为只遍历5的倍数所以不会超时
         * @param n
         * @return
         */
        // 求[1,n]的每个数有多少个5
        public int trailingZeroes1(int n) {
            if (n < 5) {
                return 0;
            }
            int count = 0;
            for (int i = 5; i <= n; i += 5) {
                for (int j = i; j % 5 == 0; j /= 5) {
                    count++;
                }
            }
            return count;
        }

        // 先求阶乘再算0的时候，会超过long能标识的最大值，会出现溢出的情况
        public int trailingZeroes2(int n) {
            if (n == 0) {
                return 0;
            }
            long res = calculate(n);
            int count = 0;
            while (res % 10 == 0) {
                count++;
                res = res / 10;
            }
            return count;
        }

        private long calculate(int n) {
            if (n == 1) {
                return 1;
            }
            return calculate(n - 1) * n;
        }
    }

}