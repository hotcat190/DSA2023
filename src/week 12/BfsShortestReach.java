import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsShortestReach {
    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here
        final int WEIGHT = 6;
        boolean[] visitedEdges = new boolean[m];
        boolean[] visitedVertex = new boolean[n];
        List<Integer> res = new ArrayList<>(n-1);
        for (int i = 0; i < n-1; i++) {
            res.add(0);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int curVertex = q.poll();
            visitedVertex[curVertex-1] = true;
            for (int edgeIndex = 0; edgeIndex < edges.size(); edgeIndex++) {
                if (visitedEdges[edgeIndex]) {
                    continue;
                }

                List<Integer> edge = edges.get(edgeIndex);
                if (edge.contains(curVertex)) {
                    int adj = edge.get(1-edge.indexOf(curVertex));
                    visitedEdges[edgeIndex] = true;
                    if (visitedVertex[adj-1]) {
                        continue;
                    }
                    q.add(adj);
                    visitedEdges[edgeIndex] = true;
                    visitedVertex[adj-1] = true;
                    res.set(getResIndex(adj, s), getCurDistance(curVertex, s, res) + WEIGHT);
                }
            }
        }
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i) == 0) {
                res.set(i, -1);
            }
        }
        return res;
    }

    private static int getResIndex(int vertex, int s) {
        return (vertex > s) ? vertex - 2 : vertex - 1;
    }

    private static int getCurDistance(int cur, int s, List<Integer> res) {
        if (cur == s) return 0;
        return res.get(getResIndex(cur, s));
    }
}
