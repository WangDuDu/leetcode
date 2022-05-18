package math;

//给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 10⁶ 
// 
// Related Topics 数组 数学 枚举 数论 👍 889 👎 0

/**
 * 204 计算质数
 */
public class CountPrimes {
    public static void main(String[] args) {
        Solution solution = new CountPrimes().new Solution();
    }

    class Solution {
        /**
         * 对于[0, n)的非负整数，除1和自身之外，可能的因数取值范围是[2, n)
         *
         * 首先遍历所有的因数(等同于遍历的是所有小于n的非负整数)，1*i、2*i、3*i这些值都不是质数
         *
         */
        public int countPrimes(int n) {

            boolean[] notPrimes = new boolean[n + 1];
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (notPrimes[i]) {
                    continue;
                }
                count++;
                /**
                 * 从 i * i 开始，因为如果 k < i，那么 k * i 在之前2~i-1的遍历中就已经被去除过了
                 * 例如
                 * (i - 1) * i若小于n，那么在遍历到i - 1的时候，i个i - 1的情况肯定已经处理过了
                 * (i - 2) * i若小于n，那么在遍历到i - 2的时候，i个i - 2的情况肯定已经处理过了
                 *
                 * 这里要注意的是i * i会超过int的最大值，所以要转成long
                 */
                for (long j = (long) (i) * i; j < n; j += i) {
                    notPrimes[(int) j] = true;
                }
            }
            return count;
        }

        /**
         * 这种方法会超时
         * @param n
         * @return
         */
        public int countPrimes2(int n) {
            if (n == 0 || n == 1 || n ==2) {
                return 0;
            }

            int res = 0;
            for (int i = 2; i < n; i++) {
                boolean isPrime = true;
                for (int j = 2; j * j <= i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) {
                    res++;
                }
            }
            return res;
        }
    }

}