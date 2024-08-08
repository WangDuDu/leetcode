package sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshuyang on 2021-4-11.
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 * 解题思路
 * 这类问题的核心思想就是利用桶排序来解决问题。
 * 这里的桶我们可以用一个List的数组来表示，数组的下标作为元素出现的频次，出现频次相同的数字放在一个桶中。
 * 寻找频率前k高的元素的过程就是将数组从后向前遍历取数字的过程，直到取够k个数字为止。
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> elementFrequencyMap = new HashMap<>();
        for (int num : nums) {
            elementFrequencyMap.put(num, elementFrequencyMap.getOrDefault(num, 0) + 1);
        }

        // 加入全部的数字都相同，那么最高频次为给定数组的元素个数，也就是说桶的下标的最大值为给定数组的元素个数。所以我们在初始化List数组的时候大小应该是给定数组大小+1
        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : elementFrequencyMap.entrySet()) {
            int frequency = entry.getValue();
            if (null == buckets[frequency]) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(entry.getKey());
        }

        // 从后往前遍历数组取数字，直到取够k个元素为止
        List<Integer> topK = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
            if (null == buckets[i]) {
                continue;
            }
            if (buckets[i].size() <= (k - topK.size())) {
                topK.addAll(buckets[i]);
            } else {
                topK.addAll(buckets[i].subList(0, k - topK.size() - 1));
            }
        }

        int[] result = new int[topK.size()];
        for (int i = 0; i < topK.size(); i++) {
            result[i] = topK.get(i);
        }

        return result;
    }
}
