import java.util.Arrays;
import java.util.List;

public class QuickSort {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        quickSort(arr);
        System.out.println(arr);
    }

    /** Return the median of the array (the element which divides the array into two equal halves). */
    public static int findMedian(List<Integer> arr) {
        quickSort(arr);
        return arr.get(arr.size()/2);
    }

    /** This implementation of quick sort use the 3-way algorithm. */
    public static void quickSort(List<Integer> arr) {
        quickSortThreeWay(arr, 0, arr.size() - 1);
    }

    private static void quickSortThreeWay(List<Integer> arr, int low, int high) {
        if (low >= high) return;

        //start partition
        int left = low;
        int pivot = arr.get(left);
        int index = low + 1;
        int right = high;
        while (index <= right) {
            // System.out.printf("index: %d, right: %d, left: %d\n", index, right, left);
            // System.out.println(arr);
            if (arr.get(index) < pivot) {
                swap(arr, index, left);
                left++;
                index++;
            }
            else if (arr.get(index) == pivot) {
                index++;
            }
            else if (arr.get(index) > pivot) {
                swap(arr, index, right);
                right--;
            }
        }
        //end partition

        //System.out.println(arr);
        quickSortThreeWay(arr, low, left - 1);
        quickSortThreeWay(arr, index, high);
    }

    private static void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
