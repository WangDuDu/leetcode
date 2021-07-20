package greddy;

import java.util.Arrays;

/**
 * Created by wangshuyang on 2021-7-20.
 */
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        if (0 == g.length || 0 == s.length) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        int result = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                result++;
            }
            j++;
        }
        return result;
    }
}
