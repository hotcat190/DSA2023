import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdOut;

public class TwoSum {

    public static void main(String[] args) {
        testTwoSum();
    }

    private static void testTwoSum() {
        for (int t = 1; t <= 8; t *= 2) {

            StdOut.println(t + "Kints.txt");

            In in = new In("C:\\jdk\\algs4-data\\" + t + "Kints.txt");
            LinearProbingHashST<Integer, Integer> hash = new LinearProbingHashST<>();

            while (!in.isEmpty()) {
                int i = in.readInt();
                if (!hash.contains(i)) hash.put(i, -i);
                if (hash.contains(-i)) {
                    StdOut.println(i + " " + -i);
                    hash.delete(i);
                    hash.delete(-i);
                }
            }
            StdOut.println();
        }
    }
}
