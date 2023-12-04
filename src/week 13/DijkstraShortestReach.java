import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DijkstraShortestReach {
    public static List<Integer> shortestReach(int n, List<List<Integer>> edges, int s) {
        // Write your code here
        List<List<Cost>> adjList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adjList.add(new LinkedList<>());
        }
        for (List<Integer> edge : edges) {
            adjList.get(edge.get(0) - 1).add(new Cost(edge.get(2), edge.get(0), edge.get(1)));
            adjList.get(edge.get(1) - 1).add(new Cost(edge.get(2), edge.get(1), edge.get(0)));
        }
        int[] distTo = new int[n];
        for (int i = 0; i < n; i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
        distTo[s-1] = 0;
        PriorityQueue<Cost> pq = new PriorityQueue<>(adjList.get(s-1));
        while (!pq.isEmpty()) {
            Cost cost = pq.poll();
            if (distTo[cost.getTo()-1] <= distTo[cost.getFrom()-1] + cost.getCost()) {
                continue;
            }
            distTo[cost.getTo()-1] = distTo[cost.getFrom()-1] + cost.getCost();
            pq.addAll(adjList.get(cost.getTo()-1));
        }
        List<Integer> res = new ArrayList<>(n-1);
        for (int i = 0; i < n; i++) {
            if (i == s-1) {
                continue;
            }
            else if (distTo[i] == Integer.MAX_VALUE) {
                res.add(-1);
            }
            else {
                res.add(distTo[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\week 13\\input07.txt"));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
                    try {
                        edges.add(
                                Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                        .map(Integer::parseInt)
                                        .collect(toList())
                        );
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = shortestReach(n, edges, s);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

class DirectedCost implements Comparable<DirectedCost> {
    private int r, t, f;

    public DirectedCost(int cost, int from, int to) {
        r = cost;
        f = from;
        t = to;
    }

    @Override
    public int compareTo(DirectedCost c) {
        if (r < c.r) return -1;
        if (r > c.r) return 1;
        if (t < c.t) return -1;
        if (t > c.t) return 1;
        if (f < c.f) return -1;
        if (f > c.f) return 1;
        return 0;
    }

    public int getCost() {
        return r;
    }
    public int getTo() {
        return t;
    }
    public int getFrom() {
        return f;
    }
}
