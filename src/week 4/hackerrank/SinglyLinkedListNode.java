public class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    public SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }

    // Print iteratively
    static void printLinkedList(SinglyLinkedListNode head) {
        SinglyLinkedListNode temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    // Print recursively
    static void printLinkedListRecursive(SinglyLinkedListNode head) {
        if (head == null) return;
        System.out.println(head.data);
        printLinkedListRecursive(head.next);
    }

    // Insert tail iterative
    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
        if (head == null) {
            head = new SinglyLinkedListNode(data);
            return head;
        }
        SinglyLinkedListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new SinglyLinkedListNode(data);
        return head;
    }

    // Insert tail recursive
    static SinglyLinkedListNode insertNodeAtTailRecursive(SinglyLinkedListNode head, int data) {
        if (head == null) {
            head = new SinglyLinkedListNode(data);
            return head;
        }
        if (head.next == null) {
            head.next = new SinglyLinkedListNode(data);
            return head;
        }
        insertNodeAtTailRecursive(head.next, data);
        return head;
    }

    // Insert head
    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode head, int data) {
        SinglyLinkedListNode temp = new SinglyLinkedListNode(data);
        temp.next = head;
        return temp;
    }

    /**
     * Insert at position iterative.
     * Insert at tail if position exceed number of nodes
     *
     * @param position start at 0.
     */
    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        if (position == 0) {
            SinglyLinkedListNode temp = new SinglyLinkedListNode(data);
            temp.next = head;
            return temp;
        }
        SinglyLinkedListNode temp = head;
        while (temp.next != null && position > 1) {
            temp = temp.next;
            position--;
        }
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    /**
     * Insert at position recursive.
     * Insert at tail if position exceed number of nodes.
     *
     * @param position start at 0.
     */
    static SinglyLinkedListNode insertNodeAtPositionRecursive(SinglyLinkedListNode head, int data, int position) {
        // Write your code here
        if (head == null || position == 0) return insertNodeAtHead(null, data);

        if (position == 1) {
            SinglyLinkedListNode temp = new SinglyLinkedListNode(data);
            temp.next = head.next;
            head.next = temp;
            return head;
        }
        insertNodeAtPositionRecursive(head.next, data, position-1);
        return head;
    }

    /**
     * Delete node iterative.
     * Return the same list if position exceed number of nodes.
     *
     * @param position start at 0.
     */
    static SinglyLinkedListNode deleteNode(SinglyLinkedListNode head, int position) {
        // Write your code here
        if (head == null) return null;

        if (position == 0) return head.next;

        if (position < 0) return head;

        SinglyLinkedListNode temp = head;
        while (temp.next != null && position > 1) {
            temp = temp.next;
            position--;
        }

        if (position == 1 && temp.next != null) {
            temp.next = temp.next.next;
        }

        return head;
    }

    /**
     * Delete node recursive.
     * Return the same list if position exceed number of nodes.
     *
     * @param position start at 0.
     */
    static SinglyLinkedListNode deleteNodeRecursive(SinglyLinkedListNode head, int position) {
        // Write your code here
        if (head == null) return null;

        if (position == 0) {
            return head.next;
        }
        head.next = deleteNodeRecursive(head.next, position-1);
        return head;
    }

    // Print in reverse recursive
    public static void reversePrint(SinglyLinkedListNode head) {
        // Write your code here
        if (head == null) {
            return;
        }
        reversePrint(head.next);
        System.out.println(head.data);
    }

    // Reverse the list recursive
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
        // Write your code here
        if (head == null) return null;
        if (head.next == null) return head;
        SinglyLinkedListNode temp = reverse(head.next);
        if (head.next.next == null) {
            head.next.next = head;
            head.next = null;
        }
        return temp;
    }

    // Compare two lists recursive
    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 == null) return true;
        else if (head1 == null || head2 == null) return false;

        if (head1.data == head2.data) {
            return compareLists(head1.next, head2.next);
        }
        else return false;
    }

    // Merge two lists recursive
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null) return head2;
        else if (head2 == null) return head1;

        if (head1.data < head2.data) {
            head1.next = mergeLists(head1.next, head2);
            return head1;
        }
        else {
            head2.next = mergeLists(head1, head2.next);
            return head2;
        }
    }

    /**
     * Get node value at k-position from tail.
     * Iterative two pass.
     */
    public static int getNode(SinglyLinkedListNode head, int positionFromTail) {
        if (head == null) {
            throw new IllegalArgumentException("Head node must not be null");
        }

        SinglyLinkedListNode temp = head;
        int count = 0;

        while (temp.next != null) {
            temp = temp.next;
            count++;
        }

        temp = head;

        while (temp != null && count > positionFromTail) {
            temp = temp.next;
            count--;
        }
        if (temp == null) {
            throw new IllegalArgumentException("Position from tail is impossible to reach");
        }
        return temp.data;
    }
}
