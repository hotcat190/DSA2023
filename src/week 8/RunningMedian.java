import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class RunningMedian {
    private static int size = 0;
    private static int sizeDiff = 0;
    private static PriorityQueue<Integer> largerPQ = new PriorityQueue<>();
    private static PriorityQueue<Integer> smallerPQ = new PriorityQueue<Integer>(11, Collections.reverseOrder());
    /*
     * Complete the 'runningMedian' function below.
     *
     * The function is expected to return a DOUBLE_ARRAY.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        List<Double> returnList = new ArrayList<>();
        for (Integer i : a) {
            add(i);
            returnList.add(getCurrentMedian());
        }
        size = 0;
        return returnList;
    }

    private static void add(Integer i) {
        if (size == 0) {
            largerPQ.add(i);
            sizeDiff++;
        }
        else if (i <= largerPQ.peek()) {
            smallerPQ.add(i);
            sizeDiff--;
        }
        else if (i > largerPQ.peek()) {
            largerPQ.add(i);
            sizeDiff++;
        }

        if (sizeDiff == -2) {
            largerPQ.add(smallerPQ.remove());
            sizeDiff = 0;
        }
        else if (sizeDiff == 2) {
            smallerPQ.add(largerPQ.remove());
            sizeDiff = 0;
        }

        size++;
    }

    private static Double getCurrentMedian() {
        if (sizeDiff == 1) return largerPQ.peek().doubleValue();
        else if (sizeDiff == -1) return smallerPQ.peek().doubleValue();
        double largerValue = largerPQ.peek().doubleValue();
        double smallerValue = smallerPQ.peek().doubleValue();
        return (largerValue + smallerValue) / 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int aCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = new ArrayList<>();

        for (int i = 0; i < aCount; i++) {
            int aItem = Integer.parseInt(bufferedReader.readLine().trim());
            a.add(aItem);
        }

        List<Double> result = runningMedian(a);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));

            if (i != result.size() - 1) {
                System.out.print("\n");
            }
        }

        System.out.println();

        bufferedReader.close();
    }
}
