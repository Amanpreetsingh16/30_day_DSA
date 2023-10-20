import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class day_16 {
    /*
     * All View set
     * Left view of Bimary tree
     * Right view of Binary tree
     * width of shadow of binary tree
     * Vertical order traversal of binary tree
     * Bottom view of Bimary Tree
     * Top view of Binary tree
     * Diagnol order traversal (Clockwise)
     * Diagnol order traversal (Anti Clockwise)
     * Vertical Order Sum 
     * Diagnol order Sum 
     */

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // LevelOrder BFS

    public void levelOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int size = 0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        while (que.size() != 0) {
            size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();
                smallAns.add(rnode.val);
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }

            }
            ans.add(smallAns);
        }
        for (var list : ans) {
            System.out.println(list);
        }

    }

    // Left view of Bimary tree

    public List<Integer> leftView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int size = 0;
        List<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();

                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }
                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }

            }

        }
        return ans;
    }

    // Right view of Binary tree

    public List<Integer> rightView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        List<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            int size = que.size();
            ans.add(que.getFirst().val);
            while (size-- > 0) {
                TreeNode rnode = que.removeFirst();

                if (rnode.right != null) {
                    que.addLast(rnode.right);
                }
                if (rnode.left != null) {
                    que.addLast(rnode.left);
                }

            }

        }
        return ans;
    }

    // width of Shadow of a Bonary Tree---- If viewed from top then how much shadow
    // will form

    public void widthOfShadow(TreeNode root, int vertl, int[] minmax) {
        if (root == null) {
            return;

        }
        minmax[0] = Math.min(minmax[0], vertl);
        minmax[1] = Math.max(minmax[1], vertl);
        widthOfShadow(root.left, vertl - 1, minmax);
        widthOfShadow(root.right, vertl + 1, minmax);

    }

    public class vPair {
        TreeNode node;
        int vl = 0;

        vPair(TreeNode node, int vl) {
            this.node = node;
            this.vl = vl;
        }
    }

    // Vertical order traversal

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int[] minmax = new int[2];
        widthOfShadow(root, 0, minmax);

        int len = minmax[1] - minmax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minmax[0])));
        for (int i = 0; i < len; i++) {
            ans.add(new ArrayList<>());
        }
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                ans.get(rp.vl).add(rp.node.val);
                if (rp.node.left != null) {
                    que.addLast(new vPair(rp.node.left, rp.vl - 1));
                }
                if (rp.node.right != null) {
                    que.addLast(new vPair(rp.node.right, rp.vl + 1));
                }
            }

        }
        return ans;

    }

    // Bottom View of Binary tree

    public ArrayList<Integer> bottomView(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] minmax = new int[2];
        widthOfShadow(root, 0, minmax);

        int len = minmax[1] - minmax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minmax[0])));
        for (int i = 0; i < len; i++) {
            ans.add(0);
        }
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;
                ans.set(vl, node.val);
                if (node.left != null) {
                    que.addLast(new vPair(rp.node.left, rp.vl - 1));
                }
                if (node.right != null) {
                    que.addLast(new vPair(rp.node.right, rp.vl + 1));
                }
            }

        }
        return ans;

    }

    // top view of Binary tree

    public ArrayList<Integer> topView(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] minmax = new int[2];
        widthOfShadow(root, 0, minmax);

        int len = minmax[1] - minmax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minmax[0])));
        for (int i = 0; i < len; i++) {
            ans.add(null);
        }
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;
                if (ans.get(vl) == null) {

                    ans.set(vl, node.val);
                }

                if (node.left != null) {
                    que.addLast(new vPair(rp.node.left, rp.vl - 1));
                }
                if (node.right != null) {
                    que.addLast(new vPair(rp.node.right, rp.vl + 1));
                }
            }

        }
        return ans;

    }

    // Diagnol Order traversal (ClockWise)

    public ArrayList<ArrayList<Integer>> diagnolOrderTraversal(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        que.addLast(root);
        while(que.size()!=0){
            int size=que.size();
            ArrayList<Integer> smallAns=new ArrayList<>();
            while(size-->0){
                TreeNode node=que.removeFirst();
                while(node!=null){
                    smallAns.add(node.val);
                    if(node.left!=null){
                        que.addLast(node.left);
                    }
                    node=node.right;

                }
            }
            ans.add(smallAns);

        }
        return ans;
        
    }

    // diagnol order traversal (Anti-clockwise)
    
    public ArrayList<ArrayList<Integer>> diagnolOrderTraversalAntiClockWise(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans=new ArrayList<>();
        que.addLast(root);
        while(que.size()!=0){
            int size=que.size();
            ArrayList<Integer> smallAns=new ArrayList<>();
            while(size-->0){
                TreeNode node=que.removeFirst();
                while(node!=null){
                    smallAns.add(node.val);
                    if(node.right!=null){
                        que.addLast(node.right);
                    }
                    node=node.left;

                }
            }
            ans.add(smallAns);

        }
        return ans;
        
    }


    // Vertical order Sum (Width technique)

    public ArrayList<Integer> VerticalOrderSum(TreeNode root) {
        LinkedList<vPair> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] minmax = new int[2];
        widthOfShadow(root, 0, minmax);

        int len = minmax[1] - minmax[0] + 1;

        que.addLast(new vPair(root, Math.abs(minmax[0])));
        for (int i = 0; i < len; i++) {
            ans.add(0);
        }
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.removeFirst();
                int vl = rp.vl;
                TreeNode node = rp.node;
                ans.set(vl, ans.get(vl)+node.val);
                if (node.left != null) {
                    que.addLast(new vPair(rp.node.left, rp.vl - 1));
                }
                if (node.right != null) {
                    que.addLast(new vPair(rp.node.right, rp.vl + 1));
                }
            }

        }
        return ans;

    }

    // Diagnol order Sum (Width technique)
    public ArrayList<Integer> diagnolOrderSum(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
       ArrayList<Integer> ans=new ArrayList<>();
        que.addLast(root);
        while(que.size()!=0){
            int size=que.size();
            int sum=0;
            while(size-->0){
                TreeNode node=que.removeFirst();
                while(node!=null){
                    sum+=node.val;
                    if(node.left!=null){
                        que.addLast(node.left);
                    }
                    node=node.right;

                }
            }
            ans.add(sum);

        }
        return ans;
        
    }

    public static void main(String[] args) {

    }
}
