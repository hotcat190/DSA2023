import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class ClosestNumbers {
    public static List<Integer> closestNumbers(List<Integer> arr) {
        arr.sort(null);

        List<Integer> return_list = new ArrayList<>();
        int currentLowestDifference = Integer.MAX_VALUE-1;
        int n = arr.size();

        for (int i = 0; i < n-1; i++) {
            int curr = arr.get(i);
            int next = arr.get(i+1);
            if (next - curr == currentLowestDifference) {
                return_list.add(curr);
                return_list.add(next);
            }
            if (next - curr < currentLowestDifference) {
                return_list.clear();
                return_list.add(curr);
                return_list.add(next);
                currentLowestDifference = next - curr;
            }
        }
        return return_list;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = 0;
        t = scanner.nextInt();
        for (int test_num = 1; test_num <= t; test_num++) {
            int n = 0;
            n = scanner.nextInt();
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                arr.add(scanner.nextInt());
            }
            System.out.println(arr);
        }
    }
}
