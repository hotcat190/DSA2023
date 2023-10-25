import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClosestNumbersMergeSort {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        List<Integer> result = closestNumbers(arr);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));

            if (i != result.size() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        bufferedReader.close();
    }

    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        mergeSort(arr);

        List<Integer> returnList = new ArrayList<>();
        int currentLowest = Integer.MAX_VALUE;
        for (int i = 0; i < arr.size()-1; i++) {
            int x = arr.get(i);
            int y = arr.get(i+1);
            int diff = y - x;
            if (diff < currentLowest) {
                currentLowest = diff;
                returnList.clear();
                returnList.add(x);
                returnList.add(y);
            }
            else if (diff == currentLowest) {
                returnList.add(x);
                returnList.add(y);
            }
        }
        return returnList;
    }

    private static void mergeSort(List<Integer> arr) {
        List<Integer> aux = new ArrayList<>(arr);
        sort(arr, aux, 0, arr.size()-1);
    }

    private static void sort(List<Integer> arr, List<Integer> aux, int low, int high) {
        if (low >= high) return;
        int mid = low + (high-low)/2;
        sort(arr, aux, low, mid);
        sort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);
    }

    private static void merge(List<Integer> arr, List<Integer> aux, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            aux.set(k, arr.get(k));
        }
        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid)                      arr.set(k, aux.get(j++));
            else if (j > high)                arr.set(k, aux.get(i++));
            else if (aux.get(i) > aux.get(j)) arr.set(k, aux.get(j++));
            else                              arr.set(k, aux.get(i++));
        }
    }
}
