import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsSpecialSubtree {
    public static int prims(int n, List<List<Integer>> edges, int start) {
        // Write your code here
        List<List<Cost>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<>());
        }
        for (List<Integer> edge : edges) {
            adjList.get(edge.get(0) - 1).add(new Cost(edge.get(2), edge.get(1)));
            adjList.get(edge.get(1) - 1).add(new Cost(edge.get(2), edge.get(0)));
        }

        int res = 0;
        boolean[] marked = new boolean[n];
        PriorityQueue<Cost> pq = new PriorityQueue<>(adjList.get(start-1));
        marked[start-1] = true;
        while (!pq.isEmpty()) {
            Cost cost = pq.poll();
            if (marked[cost.getVertex()-1]) {
                continue;
            }
            res += cost.getCost();
            marked[cost.getVertex()-1] = true;
            pq.addAll(adjList.get(cost.getVertex()-1));
        }
        return res;
    }
}

class Cost implements Comparable<Cost> {
    private int r, v;

    public Cost(int cost, int vertex) {
        r = cost;
        v = vertex;
    }

    @Override
    public int compareTo(Cost c) {
        if (r < c.r) return -1;
        if (r> c.r) return 1;
        if (v < c.v) return -1;
        if (v > c.v) return 1;
        return 0;
    }

    public int getCost() {
        return r;
    }
    public int getVertex() {
        return v;
    }
}