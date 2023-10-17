import java.util.ArrayList;
import java.util.LinkedList;

public class day_15 {

    /*
     * Binary Search Tree
     * size of a BST
     * height of a BST
     * Maximum of a BST
     * Minimum of a BST
     * find a node in tree
     * node to root path return type ArrayList
     * Lowest common ancestor in Binary Search tree
     * 
     */

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // size of BST

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    // height of BST
    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    // Maximum of a BST

    public static int Maximum(TreeNode root) {
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr.val;
    }

    // Minimuum of BST

    public static int Minimun(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr.val;
    }

    // Find a node in BST

    public static boolean find(TreeNode root, int data) {
        TreeNode curr = root;
        if (curr.val == data) {
            return true;
        } else if (curr.val < data) {
            curr = curr.right;
        } else {
            curr = curr.left;
        }
        return false;
    }

    // node to root path returning arraylist
    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        TreeNode curr = root;
        boolean flag=false;
        while(curr!=null){
            ans.add(curr);
            if (curr.val == data) {
                flag=true;
                break;
            } else if (curr.val < data) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        if(!flag){
            ans.clear();
        }
        return ans;

    }

    // Lowest Common Ancestor BST
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val < p.val && curr.val < q.val) {
                curr = curr.right;
            } else if (curr.val > p.val && curr.val > q.val) {
                curr = curr.left;
            } else {
                return curr;
            }
        }
        return null;
    }


    // BST Iterator
    //leecode 173           

    class BSTIterator {
        private LinkedList<TreeNode> st=new LinkedList<>();  // we will use addFirst, removeFirst, 
        public BSTIterator(TreeNode root) {
            addAllLeft(root);
        }
        private void addAllLeft(TreeNode node){
            while(node!=null){
                this.st.addFirst(node);
                node=node.left;
            } 
        }
        
        public int next() {
        TreeNode rnode=this.st.removeFirst();
        addAllLeft(rnode.right);
        return rnode.val;
        }
        
        public boolean hasNext() {
            return this.st.size()!=0;
        }
    }


    public static void main(String[] args) {

    }
}
