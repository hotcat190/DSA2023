import java.util.List;
import java.util.ArrayList;

class CountingSort {

    /*
     * Complete the 'countingSort' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        List<Integer> countArr = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            countArr.add(0);
        }
        for (int i = 0; i < arr.size(); i++) {
            int temp = countArr.get(arr.get(i));
            countArr.set(arr.get(i), temp + 1);
        }
        return countArr;
    }

}