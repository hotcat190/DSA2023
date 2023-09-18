import java.util.List;

public class NewYearChaos {
    public static void minimumBribes(List<Integer> q) {
        int n = q.size();
        int total_bribes_count = 0;
        final int MAX_BRIBE = 2;

        for (int i = n-1; i >= 0; i--) {
            int curr_num = q.get(i);
            if (curr_num - (i+1) > MAX_BRIBE) {
                System.out.println("Too chaotic");
                return;
            }
            int curr_range = Math.max(0, curr_num - MAX_BRIBE); // Farthest possibly bribed index relative to current number
            for (int j = curr_range; j < i; j++) {
                if (q.get(j) > curr_num) {
                    total_bribes_count++;
                }
            }
        }

        System.out.println(total_bribes_count);
    }
}