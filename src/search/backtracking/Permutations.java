package search.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangshuyang on 2021-8-6.
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        int[] visiteds = new int[nums.length];
        dfs(combinations, combination, nums, visiteds);
        return combinations;
    }

    private void dfs(List<List<Integer>> combinations, List<Integer> combination, int[] nums, int[] visiteds) {
        if (combination.size() == nums.length) {
            // 这里必须实例化一个新的ArrayList，否则combination最后会是空List，原因是实际上用的是同一个combination对象，在回溯的时候最后会将combination清空，这样combinations里面的所有combination都是空的List
            combinations.add(new ArrayList<>(combination));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visiteds[i] == 0) {
                combination.add(nums[i]);
                visiteds[i] = 1;
                dfs(combinations, combination, nums, visiteds);
                visiteds[i] = 0;
                combination.remove(combination.size() - 1);
            }
        }
    }
}
