import java.util.List;

public class ConnectedCells {
    private static int n, m;
    private static int cnt = 0;
    private static int max = 0;
    private static final int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int ADJ = 8;

    public static int connectedCell(List<List<Integer>> matrix) {
        // Write your code here
        n = matrix.size();
        m = matrix.get(0).size();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int filled = matrix.get(i).get(j);
                if (filled == 1 && !visited[i][j]) {
                    dfs(i, j, visited, matrix);
                    cnt = 0;
                }
            }
        }
        return max;
    }

    private static void dfs(int i, int j, boolean[][] visited, List<List<Integer>> matrix) {
        visited[i][j] = true;
        cnt++;
        max = Math.max(max, cnt);
        for (int adj = 0; adj < ADJ; adj++) {
            int x = j + dx[adj], y = i + dy[adj];
            if (!valid(y, x, n, m)) {
                continue;
            }
            else if (matrix.get(y).get(x) == 0) {
                continue;
            }
            else if (visited[y][x]) {
                continue;
            }
            dfs(y, x, visited, matrix);
        }
    }

    private static boolean valid(int i, int j, int n, int m) {
        return (i >= 0 && j >= 0 && i < n && j < m);
    }
}
