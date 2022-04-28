package recursion.longestSubsequence;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最长数对链
 */

public class MaximumLengthOfPairChain {

    /**
     * 递归解法
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int res = Integer.MIN_VALUE;
        int[] df = new int[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            df[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    df[i] = Math.max(df[i], df[j] + 1);
                }
            }
            res = Math.max(res, df[i]);
        }
        return res;
    }

    /**
     * 贪心解法
     * @param pairs
     * @return
     */
    public int findLongestChain2(int[][] pairs) {
        if (pairs.length == 0 || pairs[0].length == 0) {
            return 0;
        }
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        List<Pair> res = new ArrayList<>();
        int curEnd = Integer.MIN_VALUE;
        for (int i = 0; i < pairs.length; i++) {
            if (curEnd < pairs[i][0]) {
                res.add(new Pair(pairs[i][0], pairs[i][1]));
                curEnd = pairs[i][1];
            }
        }
        return res.size();
    }


}
