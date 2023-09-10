import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class ThreeSum {

    public static void main(String[] args) {
        testThreeSum();
    }

    private static void testThreeSum() {
        for (int t = 1; t <= 8; t *= 2) {

            StdOut.println(t + "Kints.txt");

            In in = new In("C:\\jdk\\algs4-data\\" + t + "Kints.txt");
            int[] a = in.readAllInts();
            int n = a.length;

            Arrays.sort(a);

            int count = 0;
            for (int i = 0; i < n-1; i++) {
                int l = i+1, r = n-1;
                while (l < r) {
                    if (a[i] + a[l] + a[r] < 0) {
                        l++;
                    }
                    else if (a[i] + a[l] + a[r] > 0) {
                        r--;
                    }
                    else {
                        //StdOut.println(a[i] + " " + a[l] + " " + a[r]);
                        int temp1 = l, temp2 = r;
                        while (l < r && a[l] == a[temp1]) l++;
                        while (l < r && a[r] == a[temp2]) r--;
                        count++;
                    }
                }
                while (i+1 < n && a[i] == a[i+1]) i++;
            }

            StdOut.println("Count: " + count);
            StdOut.println();
        }
    }
}
