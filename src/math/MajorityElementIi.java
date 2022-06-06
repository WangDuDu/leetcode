package math;

//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,2,3]
//输出：[3] 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2]
//输出：[1,2] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。 
// Related Topics 数组 哈希表 计数 排序 👍 596 👎 0


import java.util.ArrayList;
import java.util.List;

public class MajorityElementIi {
    public static void main(String[] args) {
        Solution solution = new MajorityElementIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> majorityElement(int[] nums) {
            int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;
            for (int num : nums) {
                // 这里要先判断有没有个两个候选者相同的情况，否则两个候选者可能值相等，影响最终的结果，例子[2, 1, 1, 3, 1, 4, 5, 6]
                // 候选数字1等于当前数字，候选数字1的计数+1，跳出循环
                if (candidate1 == num) {
                    count1++;
                    continue;
                }
                // 候选数字2等于当前数字，候选数字2的计数+1，跳出循环
                if (candidate2 == num) {
                    count2++;
                    continue;
                }
                // 候选数字1的计数为0，换候选数字为当前的数字，跳出循环
                if (count1 == 0) {
                    candidate1 = num;
                    count1++;
                    continue;
                }
                // 候选数字1的计数为0，换候选数字为当前的数字，跳出循环
                if (count2 == 0) {
                    candidate2 = num;
                    count2++;
                    continue;
                }
                // 当前数字和候选数字1、候选数字2均不相同，候选数字1和候选数字2的技术均-1，遍历下一个数字
                count1--;
                count2--;
            }

            // 重头遍历，校验两个候选数字是不是个数超过1/3
            count1 = 0;
            count2 = 0;
            for (int num : nums) {
                if (num == candidate1) {
                    count1++;
                    continue;
                }

                if (num == candidate2) {
                    count2++;
                    continue;
                }
            }
            List<Integer> res = new ArrayList<>();
            if (count1 > nums.length / 3) {
                res.add(candidate1);
            }
            if (count2 > nums.length / 3) {
                res.add(candidate2);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}