import java.util.*;

public class SimpleTextEditor {
    private StringBuilder stringBuilder;
    private final Stack<StringBuilder> stringStack;

    public SimpleTextEditor() {
        this.stringBuilder = new StringBuilder();
        this.stringStack = new Stack<>();
        this.stringStack.push(new StringBuilder(this.stringBuilder));
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        SimpleTextEditor simpleTextEditor = new SimpleTextEditor();
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int t = scanner.nextInt();
            if (t == 1) {
                simpleTextEditor.append(scanner.next());
            }
            else if (t == 2) {
                simpleTextEditor.delete(scanner.nextInt());
            }
            else if (t == 3) {
                simpleTextEditor.print(scanner.nextInt());
            }
            else if (t == 4) {
                simpleTextEditor.undo();
            }
            if (i == q-1) break;
            //System.out.println(simpleTextEditor.stringBuilder);
        }

        scanner.close();
    }

    public void append(String value) {
        this.stringBuilder.append(value);
        stringStack.push(new StringBuilder(this.stringBuilder));
    }

    public void delete(int k) {
        int n = stringBuilder.length();
        this.stringBuilder.delete(n - k, n);
        stringStack.push(new StringBuilder(this.stringBuilder));
    }

    public void print(int k) {
        System.out.println(this.stringBuilder.charAt(k-1));
    }

    public void undo() {
        if (stringStack.isEmpty()) {
            return;
        }
        this.stringStack.pop();
        this.stringBuilder = new StringBuilder(this.stringStack.peek());
    }

}
