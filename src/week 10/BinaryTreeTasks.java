import java.util.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class BinaryTreeTasks {
    /** Return tree's height. */
    public static int height(Node root) {
        return postOrder(root, 0);
    }

    /** Helper method for height(Node). */
    private static int postOrder(Node root, int depth) {
        if (root.left == null && root.right == null) return depth;
        int left = 0, right = 0;
        if (root.left != null) left = postOrder(root.left, depth + 1);
        if (root.right != null) right = postOrder(root.right, depth + 1);
        return Math.max(left, right);
    }

    /** Return lowest common ancestor of v1 and v2. */
    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.
        if (root == null) return null;
        int larger = Math.max(v1, v2);
        int smaller = Math.min(v1, v2);
        if (root.data >= smaller && root.data <= larger) return root;
        if (root.data > larger) return lca(root.left, v1, v2);
        return lca(root.right, v1, v2);
    }

    /** Insert a Node into tree. */
    public static Node insert(Node root,int data) {
        if (root == null) root = new Node(data);
        else if (root.data > data) root.left = insert(root.left, data);
        else root.right = insert(root.right, data);
        return root;
    }

    /** Check for valid BST. */
    static boolean checkBST(Node root) {
        if (root == null) return true;
        return checkInRange(root.left, Integer.MIN_VALUE, root.data)
                && checkInRange(root.right, root.data, Integer.MAX_VALUE);
    }

    /** Helper method for checkBST(). */
    private static boolean checkInRange(Node node, int leftBound, int rightBound) {
        if (node == null) return true;
        if (node.data <= leftBound || node.data >= rightBound) return false;
        return checkInRange(node.left, leftBound, node.data)
                && checkInRange(node.right, node.data, rightBound);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}