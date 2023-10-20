import java.util.LinkedList;

public class day_18 {
    /*
     * Tree Conversion set
     * Binary tree to DLL
     * Binary tree to DLL iterativre approch
     * Binary tree to CDLL
     * Binary tree to CDLL iterativre approch
     * Binary to BST Conversion
     */

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class Node {
        int val = 0;
        Node left = null;
        Node right = null;

        Node(int val) {
            this.val = val;
        }
    }

    public static class ListNode {
        int data = 0;
        ListNode next = null;
        ListNode prev = null;

        ListNode(int data) {
            this.data = data;
        }

    }

    // Binary tree to Doubally Linkred List

    static Node prev; // also uded in CDLL

    void bToDLL_(Node root) {

        if (root == null) {
            return;
        }
        bToDLL_(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;

        bToDLL_(root.right);
    }

    Node bToDLL(Node root) {

        Node dummy = new Node(-1);
        prev = dummy;
        Node head = dummy.right;
        head.left = dummy.right = null;
        return head;
    }

    // Binary Tree to DLL Iterative Solution
    public static void insertAllLeft(LinkedList<Node> st, Node node) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }

    }

    public static Node bToDLLII(Node root) {

        Node dummy = new Node(-1);
        Node prev1 = dummy;
        LinkedList<Node> st = new LinkedList<>();
        insertAllLeft(st, root);
        while (st.size() != 0) {
            Node curr = st.removeFirst();
            prev1.right = curr;
            curr.left = prev1;
            prev1 = curr;
            insertAllLeft(st, curr.right);
        }
        Node head = dummy.right;
        head.left = dummy.right = null;
        return head;
    }

    // Binary tree to Circular Doubally Linkred List

    void bToCDLL_(Node root) {
        // Your code here
        if (root == null) {
            return;
        }
        bToCDLL_(root.left);
        prev.right = root;
        root.left = prev;
        prev = root;

        bToCDLL_(root.right);
    }

    Node bTreeToClist(Node root) {
        // your code here
        // Your code here
        Node dummy = new Node(-1);
        prev = dummy;
        bToCDLL_(root);
        Node head = dummy.right;
        head.left = dummy.right = null;
        head.left = prev;
        prev.right = head;
        return head;

    }

    // Binary Tree to CDLL Iterative Solution

    Node bTreeToClistII(Node root) {

        Node dummy = new Node(-1);
        Node prev1 = dummy;
        LinkedList<Node> st = new LinkedList<>();
        insertAllLeft(st, root);
        while (st.size() != 0) {
            Node curr = st.removeFirst();
            prev1.right = curr;
            curr.left = prev1;
            prev1 = curr;
            insertAllLeft(st, curr.right);
        }
        Node head = dummy.right;
        head.left = dummy.right = null;
        head.left = prev1;
        prev1.right = head;
        return head;

    }


    // Binary to BST Conversion

    public static Node middleNode(Node head) {
        if (head == null || head.right == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast.right != null && fast.right.right != null) {
            slow = slow.right;
            fast = fast.right.right;

        }
        return slow;
    }

    public static Node mergeDLL(Node h1, Node h2) {
        if (h1 == null || h2 == null) {
            return h1 != null ? h1 : h2;
        }
        Node dummy = new Node(-1);
        Node p = dummy, c1 = h1, c2 = h2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                p.right = c1;
                c1.left = p;
                c1=c1.right;
            } else {
                p.right = c2;
                c2.left=p;
                c2 = c2.right;
            }
            p = p.right;
        }
       if(c1!=null){
        p.right=c1;
        c1.left=p;
       }
       if(c2!=null){
        p.right=c2;
        c2.left=p;
       }
       Node head=dummy.right;
       head.left=dummy.right=null;

        return head;
    }
    public static Node sortDLL(Node head){
        if (head == null || head.right == null) {
            return head;
        }
        Node mid = middleNode(head);
        Node nhead = mid.right;
        mid.right = mid.left= null;
        return mergeDLL(sortDLL(head), sortDLL(nhead));
    }



    public static Node DLLToBST(Node head){
        if(head==null || head.right==null){
            return head;
        }
        Node root=middleNode(head);
        Node leftDLLHead=head;
        Node rightDLLHead=head;
        root.left.right=null;
        root.right.left=null;
        root.left=root.right=null;

        root.left=DLLToBST(leftDLLHead);

        root.right=DLLToBST(rightDLLHead);
        

        return root;
    }

    public static Node binary_To_BST(Node root){
        Node head=bToDLLII(root);
        head=sortDLL(head);
        return DLLToBST(head);
    }

}
