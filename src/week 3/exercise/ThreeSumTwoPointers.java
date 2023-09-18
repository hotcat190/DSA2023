import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ThreeSumTwoPointers {

    public static void main(String[] args) throws FileNotFoundException {
        testThreeSum();
    }

    private static void testThreeSum() throws FileNotFoundException {
        for (int t = 1; t <= 8; t *= 2) {

            System.out.println(t + "Kints.txt");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\jdk\\algs4-data\\" + t + "Kints.txt")));
            try {
                String line;
                List<Integer> a = new ArrayList<>();
                while ((line = bufferedReader.readLine()) != null) {
                    a.add(Integer.parseInt(line.replace(" ", "")));
                }
                int n = a.size();

                // sort from smaller to larger
                a.sort(null);

                int count = 0;
                for (int i = 0; i < n - 1; i++) {
                    // fix one number and check the sum of other two with two pointers
                    int l = i + 1, r = n - 1;
                    while (l < r) {
                        if (a.get(i) + a.get(l) + a.get(r) < 0) {
                            // if sum is less we need to increase it, so move left pointer up
                            l++;
                        } else if (a.get(i) + a.get(l) + a.get(r) > 0) {
                            // if sum is more we need to decrease it, so move right pointer down
                            r--;
                        } else {
                            //StdOut.println(a.get(i) + " " + a.get(l) + " " + a.get(r));
                            int temp1 = l, temp2 = r;
                            while (l < r && Objects.equals( a.get(l), a.get(temp1) )) l++;
                            while (l < r && Objects.equals( a.get(r), a.get(temp2) )) r--;
                            count++;
                        }
                    }
                    while (i + 1 < n && Objects.equals( a.get(i), a.get(i + 1) )) i++;
                }

                System.out.println("Count: " + count);
                System.out.println();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
