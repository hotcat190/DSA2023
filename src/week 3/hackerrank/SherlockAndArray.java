import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SherlockAndArray {
    // Time complexity: N/2 + N/2 + N/2 = ~N
    public static String balancedSums(List<Integer> arr) {
        int n = arr.size();
        if (n == 1) return "YES";
        int mid = n/2;
        long leftSum = findLeftSum(arr, mid);
        long rightSum = findRightSum(arr, mid);
        while (mid > 0 && mid < n-1) {
            if (leftSum == rightSum) {
                return "YES";
            }
            if (leftSum > rightSum) {
                leftSum -= arr.get(mid-1);
                rightSum += arr.get(mid);
                if (leftSum < rightSum) {
                    return "NO";
                }
                mid--;
            }
            if (leftSum < rightSum) {
                leftSum += arr.get(mid);
                rightSum -= arr.get(mid+1);
                if (leftSum > rightSum) {
                    return "NO";
                }
                mid++;
            }
        }
        if ((mid == 0 && rightSum == 0) || (mid == n-1 && leftSum == 0)) return "YES";
        return "NO";
    }

    private static long findLeftSum(List<Integer> arr, int index) {
        long sum = 0;
        for (int i = 0; i < index; i++) {
            sum += arr.get(i);
        }
        return sum;
    }

    private static long findRightSum(List<Integer> arr, int index) {
        long sum = 0;
        for (int i = index + 1; i < arr.size(); i++) {
            sum += arr.get(i);
        }
        return sum;
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
            System.out.println(balancedSums(arr));
        }
    }
}
