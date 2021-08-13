package recursion.matrixpath;

/**
 * Created by wangshuyang on 2021-8-12.
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }
        int[][] df = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    df[i][j] = 1;
                } else {
                    df[i][j] = df[i - 1][j] + df[i][j - 1];
                }
            }
        }
        return df[m - 1][n - 1];
    }
}
