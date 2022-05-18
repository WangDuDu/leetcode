package math;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName GreatestCommonDivisor.java
 * @Description 最大公约数
 * @createTime 2022年05月18日 22:00:00
 */

/**
 * 使用辗转相除法
 */
public class GreatestCommonDivisor {

    public static void main(String[] args) {
        Solution solution = new GreatestCommonDivisor().new Solution();
        System.out.println(solution.gcd(18, 9));
    }

    class Solution {
        public int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }
    }

    /**
     * 使用位操作和减法求最大公约数
     *
     * 对于 a 和 b 的最大公约数 f(a, b)，有：
     *      如果 a 和 b 均为偶数，f(a, b) = 2*f(a/2, b/2);
     *      如果 a 是偶数 b 是奇数，f(a, b) = f(a/2, b);
     *      如果 b 是偶数 a 是奇数，f(a, b) = f(a, b/2);
     *      如果 a 和 b 均为奇数，f(a, b) = f(b, a-b);
     */
    class Solution2 {
        public int gcd(int a, int b) {
            if (a < b) {
                return gcd(b, a);
            }

            if (b == 0) {
                return a;
            }

            if (isEven(a) && isEven(b)) {
                return 2 * gcd(a >> 1, b >> 1);
            } else if (isEven(a) && isUneven(b)) {
                return gcd(a >> 1, b);
            } else if (isUneven(a) && isEven(b)) {
                return gcd(a, b >> 1);
            } else {
                return gcd(b, a / b);
            }
        }

        private boolean isUneven(int num) {
            return num % 2 == 1;
        }

        private boolean isEven(int num) {
            return num % 2 == 0;
        }
    }



}
