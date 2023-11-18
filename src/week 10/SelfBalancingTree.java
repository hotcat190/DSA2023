import java.util.LinkedList;
import java.util.Queue;

public class SelfBalancingTree {
    static class Node {
        int val;
        int ht;
        Node left;
        Node right;
    }

    static Node insert(Node root, int val) {
        if (root == null) {
            root = new Node();
            root.val = val;
            root.ht = 0;
            root.left = null;
            root.right = null;
            return root;
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
            if (balanceFactor(root) == 2) {
                if (balanceFactor(root.left) == -1) {
                    root.left = rotateLeft(root.left);
                }
                root = rotateRight(root);
            }
        } else if (val > root.val) {
            root.right = insert(root.right, val);
            if (balanceFactor(root) == -2) {
                if (balanceFactor(root.right) == 1) {
                    root.right = rotateRight(root.right);
                }
                root = rotateLeft(root);
            }
        }
        root.ht = 1 + Math.max(height(root.left), height(root.right));
        return root;
    }

    static Node rotateLeft(Node root) {
        Node newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        root.ht = 1 + Math.max(height(root.left), height(root.right));
        newRoot.ht = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    static Node rotateRight(Node root) {
        Node newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        root.ht = 1 + Math.max(height(root.left), height(root.right));
        newRoot.ht = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    static int height(Node node) {
        if (node == null) return -1;
        return node.ht;
    }

    static int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    static void inOrder(Node root) {
        if (root.left != null) inOrder(root.left);
        System.out.print(root.val + "(BF=" + balanceFactor(root) + ") ");
        if (root.right != null) inOrder(root.right);
    }

    static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Node node = q.remove();
                System.out.print(node.val + "(BF=" + balanceFactor(node) + ") ");
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Node root = null;
        int[] arr = new int[]{3, 2, 4, 5, 6};
        for (int val : arr) {
            root = insert(root, val);
        }
        inOrder(root);
        System.out.println();
        levelOrder(root);
    }
}
