public class Dragon {
    public static void main(String[] args) {
        System.out.println(dragonFractal(Integer.parseInt(args[0])));
    }
    public static String dragonFractal(int n) {
        String prev = "F";
        String curr = "FLF";
        for (int i = 1; i <= n; i++) {
            StringBuilder temp = new StringBuilder(curr);
            temp.reverse();
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == 'L') temp.setCharAt(j, 'R');
                else if (temp.charAt(j) == 'R') temp.setCharAt(j, 'L');
            }
            String next = curr + "L" + temp;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
