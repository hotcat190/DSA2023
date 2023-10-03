import java.util.Stack;

public class BalancedBrackets {
    public static String isBalanced(String s) {
        // Write your code here
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            }
            else if (stack.isEmpty())
                return "NO";
            else if (s.charAt(i) == ')' && stack.peek() != '(')
                return "NO";
            else if (s.charAt(i) == ']' && stack.peek() != '[')
                return "NO";
            else if (s.charAt(i) == '}' && stack.peek() != '{')
                return "NO";
            else stack.pop();
        }
        if (stack.isEmpty()) return "YES";
        return "NO";
    }
}