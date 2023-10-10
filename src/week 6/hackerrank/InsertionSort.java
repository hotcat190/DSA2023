import java.util.List;

public class InsertionSort {
    /** Insert the last element into its correct position. */
    public static void insertionSort1(int n, List<Integer> arr) {
        // Write your code here
        int target = arr.get(n - 1);
        for (int i = n - 1 ; i >= 0; i--) {
            if (i == 0) {
                arr.set(0, target);
            } else if (arr.get(i - 1) <= target) {
                arr.set(i, target);
                print(arr);
                break;
            } else {
                arr.set(i, arr.get(i-1));
            }
            print(arr);
        }
    }

    /** Sort an array. */
    public static void insertionSort2(int n, List<Integer> arr) {
        // Write your code here
        if (n == 1) return;
        for (int i = 1; i < n; i++) {
            for (int j = i ; j > 0; j--) {
                if (arr.get(j - 1) > arr.get(j)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j - 1));
                    arr.set(j - 1, temp);
                }
            }
            print(arr);
        }
    }

    private static void print(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}