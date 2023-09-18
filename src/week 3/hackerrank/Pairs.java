import java.util.*;

public class Pairs {
    public static int pairs(int k, List<Integer> arr) {
        int count_pairs = 0;
        int n = arr.size();

        Set<Integer> set = new HashSet<>(arr);

        for (int i = 0; i < n-1; i++) {
            int curr = arr.get(i);
            if (set.contains(curr)) {
                if (set.contains(curr+k)) {
                    count_pairs++;
                }
                if (set.contains(curr-k)) {
                    count_pairs++;
                }
                set.remove(curr);
            }
        }

        return count_pairs;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> arr = new ArrayList<>();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            arr.add(scanner.nextInt());
        }

        System.out.println(pairs(k, arr));
    }
}
