package dynamicprogramming.fibonacci;

/**
 * Created by wangshuyang on 2021-8-8.
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        // d[i - 2]也就是最后到达i的时候走了两步的所有解
        int pre1 = 2;
        // d[i - 1]也就是最后到达i的时候走了一步的所有解
        int pre2 = 1;
        for (int i = 2; i < n; i++) {
            int cur = pre1 + pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
