package math;

//给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。 
//
// 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4ˣ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 16
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：true
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
// Related Topics 位运算 递归 数学 👍 301 👎 0


public class PowerOfFour {
    public static void main(String[] args) {
        Solution solution = new PowerOfFour().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 首先如果一个数是4的幂次方，那么他一定是2的幂次方，所以可以先判断这个数是不是2的幂次方
         * 4的幂次方的数在2的幂次方的基础上有一个特征就是，4的幂次方1在奇数位上例如4：100，16：10000，64：1000000
         * 所以我们要做的就是将高位1在奇数位上的2的幂次方筛选出来，高位1在奇数微商的2的幂次方，只要和奇数位都是都是1的二进制数相与就是它本身，
         * 在给定取值范围内奇数位为1，偶数位为0的数是1010101010101010101010101010101，转换成16进制切分成8个4位的数为0x55555555
         */
        public boolean isPowerOfFour(int n) {
            return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) == n;
        }

        /**
         * 首先如果一个数是4的幂次方，那么他一定是2的幂次方，所以可以先判断这个数是不是2的幂次方
         * 在此基础上，如果这个数是4的幂次方，那么在减1后一定可以被3整除
         *
         * @param n
         * @return
         */
        public boolean isPowerOfFour1(int n) {
            return n > 0 && (n & (n - 1)) == 0 && (n - 1) % 3 == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}