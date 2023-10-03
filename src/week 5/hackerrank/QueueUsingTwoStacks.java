import java.util.Scanner;
import java.util.Stack;

public class QueueUsingTwoStacks {
    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> outStack = new Stack<>();

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named QueueUsingTwoStacks. */
        Scanner scanner = new Scanner(System.in);
        QueueUsingTwoStacks solution = new QueueUsingTwoStacks();
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int type = scanner.nextInt();
            if (type == 1) {
                int value = scanner.nextInt();
                solution.enqueue(value);
            } else if (type == 2) {
                solution.dequeue();
            } else if (type == 3) {
                solution.print();
            }
        }
        scanner.close();
    }

    private void transfer() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }

    public void enqueue(int value) {
        inStack.push(value);
        if (outStack.isEmpty()) {
            this.transfer();
        }
    }

    public void dequeue() {
        if (!outStack.isEmpty()) {
            outStack.pop();
            if (outStack.isEmpty()) {
                this.transfer();
            }
        }
    }

    public void print() {
        if (outStack.isEmpty()) return;
        System.out.println(outStack.peek());
    }
}
