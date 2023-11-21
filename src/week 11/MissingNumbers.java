import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class MissingNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String[] brrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> brr = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrTemp[i]);
            brr.add(brrItem);
        }

        List<Integer> result = HashTableSolution.missingNumbers(arr, brr);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));

            if (i != result.size() - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        bufferedReader.close();
    }
}

class HashTableSolution {
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        Hashtable<Integer, Integer> htA = new Hashtable<>();
        for (int i : arr) {
            if (!htA.containsKey(i)) {
                htA.put(i, 1);
            } else {
                htA.put(i, htA.get(i) + 1);
            }
        }
        Hashtable<Integer, Integer> htB = new Hashtable<>();
        for (int i : brr) {
            if (!htB.containsKey(i)) {
                htB.put(i, 1);
            } else {
                htB.put(i, htB.get(i) + 1);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (Enumeration<Integer> e = htB.keys(); e.hasMoreElements();) {
            int k = e.nextElement();
            if (!htA.containsKey(k)) {
                res.add(k);
                continue;
            }
            int valA = htA.get(k);
            int valB = htB.get(k);
            if (valA < valB) {
                res.add(k);
            }
        }
        res.sort(null);
        return res;
    }
}

class SortSolution {
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        arr.sort(null);
        brr.sort(null);
        int indexA = 0;
        int indexB = 0;
        List<Integer> res = new ArrayList<>();
        while (indexA < arr.size() && indexB < brr.size()) {
            while (arr.get(indexA).equals(brr.get(indexB))) {
                indexA++;
                indexB++;
                if (indexA >= arr.size()) {
                    break;
                }
            }
            if (indexB >= brr.size()) {
                break;
            }
            int valB = brr.get(indexB);
            res.add(valB);
            while (indexB < brr.size() && brr.get(indexB).equals(valB)) {
                indexB++;
            }
        }
        return res;
    }
}

class CountSolution {
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        // Write your code here
        int[] cntA = getCountingArray(arr);
        int[] cntB = getCountingArray(brr);

        List<Integer> res = new ArrayList<>();

        int indexA = 0;
        int indexB = 0;
        int minA = cntA[indexA++];
        int minB = cntB[indexB++];

        if (minB < minA) {
            while (indexB < minA - minB + 1) {
                if (cntB[indexB] > 0) {
                    res.add(indexB + minB - 1);
                }
                indexB++;
            }
        }

        while(indexA < cntA.length && indexB < cntB.length) {
            if (cntB[indexB] > cntA[indexA]) {
                res.add(indexB + minB - 1);
            }
            indexA++;
            indexB++;
        }
        while (indexB < cntB.length) {
            res.add(indexB + minB - 1);
            indexB++;
        }
        return res;
    }

    private static int[] getCountingArray(List<Integer> list) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Integer i : list) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        int range = max - min;
        int[] cnt = new int[range + 2];
        cnt[0] = min;
        for (Integer i : list) {
            cnt[i - min + 1]++;
        }
        return cnt;
    }
}
