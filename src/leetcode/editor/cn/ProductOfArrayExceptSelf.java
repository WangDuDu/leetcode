package leetcode.editor.cn;

//给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。 
//
// 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。 
//
// 请不要使用除法，且在 O(n) 时间复杂度内完成此题。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4]
//输出: [24,12,8,6]
// 
//
// 示例 2: 
//
// 
//输入: nums = [-1,1,0,-3,3]
//输出: [0,0,9,0,0]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁵ 
// -30 <= nums[i] <= 30 
// 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内 
// 
//
// 
//
// 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。） 
// Related Topics 数组 前缀和 👍 1172 👎 0


public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Solution solution = new ProductOfArrayExceptSelf().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 本题要求时间复杂度为O(n)，所以不能采用两层for循环嵌套的方式求解，O(n)意味着最多只能有一次循环
         * 采用两次循环遍历，第一次循环计算当前值左边所有数的乘积。第二次循环计算当前值右边所有数的乘积，再把两数相乘就是最后的结果了。
         *
         * @param nums
         * @return
         */
        public int[] productExceptSelf(int[] nums) {
            int length = nums.length;

            int[] answer = new int[nums.length];
            for (int i = 0; i < length; i++) {
                answer[i] = 1;
            }

            int left = 1;
            for (int i = 1; i < length; i++) {
                left *= nums[i - 1];
                answer[i] *= left;
            }

            int right = 1;
            for (int i = length - 2; i >= 0; i--) {
                right *= nums[i + 1];
                answer[i] *= right;
            }

            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}