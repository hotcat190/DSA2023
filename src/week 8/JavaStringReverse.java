import java.io.*;
import java.util.*;

public class JavaStringReverse {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        /* Enter your code here. Print output to STDOUT. */
        System.out.println(A.contentEquals(new StringBuilder(A).reverse()) ? "Yes" : "No");
    }
}

