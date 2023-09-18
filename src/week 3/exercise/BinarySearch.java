import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int[] a, int number) {
        int n = a.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a[mid] == number) {
                return mid;
            }
            if (a[mid] < number) {
                l = mid + 1;
            }
            if (a[mid] > number) {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        final int MIN_ARR_LEN = 5;
        final int MAX_ARR_LEN = 25;
        System.out.println("Generating random array length in range (" + MIN_ARR_LEN + ", " + MAX_ARR_LEN + ")");
        int n = ThreadLocalRandom.current().nextInt(MIN_ARR_LEN, MAX_ARR_LEN + 1);
        System.out.println("Array length: " + n);

        final int MIN_NUM = 1;
        final int MAX_NUM = 100;
        System.out.println("Generating random numbers in range (" + MIN_NUM + ", " + MAX_NUM + ") for each index");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt(MIN_NUM, MAX_NUM + 1);
        }
        System.out.println("Generated array:");
        System.out.println(Arrays.toString(a));

        System.out.println("Sorting array");
        Arrays.sort(a);
        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(a));

        System.out.println("Picking a random number in range (" + MIN_NUM + ", " + MAX_NUM + ") as target");
        int target = ThreadLocalRandom.current().nextInt(MIN_NUM, MAX_NUM + 1);
        System.out.println("Target: " + target);

        System.out.println("Searching for target in array");
        int target_index = binarySearch(a, target);
        int target_index_ref = Arrays.binarySearch(a, target);

        System.out.println("Result: " + target_index);
        System.out.println("Reference: " + target_index_ref);
    }
}