package math;

//给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：24
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,-2,-3]
//输出：-6
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10⁴ 
// -1000 <= nums[i] <= 1000 
// 
// Related Topics 数组 数学 排序 👍 379 👎 0


import java.util.Arrays;

public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) {
        Solution solution = new MaximumProductOfThreeNumbers().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 三个数的最大乘积有两种情况，三个最大正数的乘积或者两个最小负数和最大整数的乘积，所以我们只要得到最大的三个数和最小的两个数即可
         *
         * @param nums
         * @return
         */
        public int maximumProduct(int[] nums) {
            int n = nums.length - 1;
            Arrays.sort(nums);
            int right = nums[n] * nums[n - 1] * nums[n - 2];
            int left = nums[0] * nums[1] * nums[n];
            return Integer.max(right, left);
        }

        public int maximumProduct1(int[] nums) {
            int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            for (int n : nums) {
                if (n > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = n;
                } else if (n > max2) {
                    max3 = max2;
                    max2 = n;
                } else if (n > max3) {
                    max3 = n;
                }

                if (n < min1) {
                    min2 = min1;
                    min1 = n;
                } else if (n < min2) {
                    min2 = n;
                }
            }
            return Math.max(max1 * max2 * max3, max1 * min1 * min2);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}