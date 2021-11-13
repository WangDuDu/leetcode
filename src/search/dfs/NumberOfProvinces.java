package search.dfs;

public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int length = isConnected.length;
        int[] visited = new int[length];
        int res = 0;
        for (int i = 0; i < length; i++) {
            if (visited[i] == 0) {
                dfs(isConnected, i, visited);
                res++;
            }
        }
        return res;

    }

    public void dfs(int[][] isConnected, int curNode, int[] visisted) {
        visisted[curNode] = 1;
        for (int i = curNode; i < isConnected.length; i++) {
            if (isConnected[curNode][i] == 1 && visisted[i] == 0) {
                dfs(isConnected, i, visisted);
            }
        }
    }
}
