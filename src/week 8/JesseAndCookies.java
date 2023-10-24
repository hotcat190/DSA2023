import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class JesseAndCookies {
    public static int cookies(int k, List<Integer> A) {
        // Write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>(A);
        int count = 0;
        while (pq.size() > 1 && pq.peek() < k) {
            int x = pq.remove();
            int y = pq.remove();
            pq.add(x + 2 * y);
            count++;
        }
        return (pq.size() == 1 && pq.peek() < k) ? -1 : count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src\\week 8\\input21.txt"));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String[] ATemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> A = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int AItem = Integer.parseInt(ATemp[i]);
            A.add(AItem);
        }

        int result = cookies(k, A);

        System.out.println(result);

        bufferedReader.close();
    }
}
