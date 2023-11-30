import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 10, 9, 6, 7, 4, 5, 2, 8};
        TreeTraversal.Node root = null;
        for (int i : arr) {
            root = TreeTraversal.insert(root, i);
            TreeTraversal.levelOrder(root);
            System.out.println();
        }
        System.out.println("Delete");
        int[] brr = new int[]{3, 5, 7, 9};
        for (int i : brr) {
            root = TreeTraversal.remove(root, i);
            TreeTraversal.levelOrder(root);
            System.out.println();
        }
    }
}

class TreeTraversal {

    public static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void inOrder(Node root) {
        if (root.left != null) inOrder(root.left);
        System.out.print(root.data + " ");
        if (root.right != null) inOrder(root.right);
    }

    public static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void levelOrder(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int qSize = q.size();
            System.out.print("Level " + depth++ + ": ");
            for (int i = 0; i < qSize; i++) {
                Node node = q.remove();
                System.out.print(node.data + " ");
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            System.out.println();
        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static Node remove(Node root, int i) {
        if (root == null) return null;
        int cmp = Integer.compare(i, root.data);
        if (cmp < 0) root.left = remove(root.left, i);
        else if (cmp > 0) root.right = remove(root.right, i);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node temp = root;
            root = min(temp.right);
            root.right = deleteMin(temp.right);
            root.left = temp.left;
        }
        return root;
    }

    public static Node min(Node root) {
        if (root == null) return null;
        if (root.left == null) return root;
        return min(root.left);
    }

    public static Node deleteMin(Node root) {
        if (root == null) return null;
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        return root;
    }
}
