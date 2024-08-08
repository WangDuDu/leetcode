package leetcode.editor.cn;

//给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。 
//
// 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：2
//解释：
//只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
//[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,10,2,9]
//输出：16
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
// Related Topics 数组 数学 排序 👍 250 👎 0


public class MinimumMovesToEqualArrayElementsIi {
    public static void main(String[] args) {
        Solution solution = new MinimumMovesToEqualArrayElementsIi().new Solution();
    }

    class Solution {
        public int minMoves2(int[] nums) {
            int mid = findKthSmallest(nums, 0, nums.length - 1);
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                res += Math.abs(nums[i] - mid);
            }
            return res;
        }

        private int findKthSmallest(int[] nums, int start, int end) {
            int k = nums.length / 2;
            while (true) {
                int partitionPos = partition(nums, start, end);
                if (partitionPos == k) {
                    break;
                }
                if (k < partitionPos) {
                    end = partitionPos - 1;
                } else {
                    start = partitionPos + 1;
                }
            }
            return nums[k];
        }

        private int partition(int[] nums, int start, int end) {
            if (start == end) {
                return start;
            }
            int l = start + 1;
            int r = end;
            while (true) {
                while (nums[l] < nums[start] && l < end) {
                    l++;
                }
                while (nums[r] > nums[start] && r > start){
                    r--;
                }
                if (l >= r) {
                    break;
                }
                swap(nums, l++, r--);
            }
            swap(nums, start, r);
            return r;
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

}