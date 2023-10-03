import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class EqualStacks {
    public static int equalStacks(Stack<Integer> h1, Stack<Integer> h2, Stack<Integer> h3) {
        // Write your code here
        int sum1 = h1.stream().mapToInt(Integer::intValue).sum();
        int sum2 = h2.stream().mapToInt(Integer::intValue).sum();
        int sum3 = h3.stream().mapToInt(Integer::intValue).sum();
        while (sum1 != sum2 || sum2 != sum3) {
            int currentLowest = Math.min(Math.min(sum1, sum2), sum3);
            if (sum1 > currentLowest) {
                sum1 -= h1.pop();
            }
            if (sum2 > currentLowest) {
                sum2 -= h2.pop();
            }
            if (sum3 > currentLowest) {
                sum3 -= h3.pop();
            }
            if (currentLowest == 0) {
                return 0;
            }
        }
        return sum1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        Integer[] h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt).toArray(Integer[]::new);

        Integer[] h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt).toArray(Integer[]::new);

        Integer[] h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt).toArray(Integer[]::new);

        Stack<Integer> s1 = new Stack<>();
        for (int i = h1.length - 1; i >= 0; i--){
            s1.push(h1[i]);
        }

        Stack<Integer> s2 = new Stack<>();
        for (int i = h2.length - 1; i >= 0; i--){
            s2.push(h2[i]);
        }

        Stack<Integer> s3 = new Stack<>();
        for (int i = h3.length - 1; i >= 0; i--){
            s3.push(h3[i]);
        }

        int result = EqualStacks.equalStacks(s1, s2, s3);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}
