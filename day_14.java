import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/*
 * size of a tree
 * height of a tree
 * Maximum of a tree
 * find a node in tree
 * node to root path return type ArrayList
 * node to root path return type boolean
 * Node to all Leaf path
 * Find all single child Node
 * Find all single child Node count
 * all nodes k distance away leetcode 863
 */
public class day_14 {
    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // size of tree

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    // height of tree
    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
    }

    // Maximum of a tree

    public static int Maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(Math.max(Maximum(root.left), Maximum(root.right)), root.val);
    }

    // Find a node in tree
    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;
        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    // node to root path returning arraylist
    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }

        if (root.val == data) {
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode> left = NodeToRootPath(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = NodeToRootPath(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    // node to root path

    public static boolean NodeToRootPath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;
        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = NodeToRootPath(root.left, data, ans) || NodeToRootPath(root.right, data, ans);
        if (res)
            ans.add(root);
        return res;
    }

    // Node to all Leaf path

    public static void nodeToAllLeafPath(TreeNode root, ArrayList<Integer> smallans,
            ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ArrayList<Integer> base = new ArrayList<>(smallans);
            base.add(root.val);
            ans.add(base);
            return;
        }
        smallans.add(root.val);
        nodeToAllLeafPath(root.left, smallans, ans);
        nodeToAllLeafPath(root.right, smallans, ans);
        smallans.remove(smallans.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> nodeToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> smallans = new ArrayList<>();
        nodeToAllLeafPath(root, smallans, ans);
        return ans;

    }

    // Find all single child Node
    public static void singleChildNode(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) {
            return;

        }

        if (root.left == null || root.right == null) {
            ans.add(root.val);
        }
        singleChildNode(root.left, ans);
        singleChildNode(root.right, ans);
    }

    // Find all single child Node count
    public static int countSingleChildNode(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        int left = countSingleChildNode(root.left);
        int right = countSingleChildNode(root.right);
        int ans = left + right;
        if (root.left == null || root.right == null) {
            ans++;
        }
        return ans;

    }

    // all nodes k distance away
    // leetcode 863
    public void kDown(TreeNode root, TreeNode blockNode, int k, List<Integer> ans) {
        if (root == null || root == blockNode || k < 0) {
            return;
        }
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        kDown(root.left, blockNode, k - 1, ans);
        kDown(root.right, blockNode, k - 1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> path = new ArrayList<>();
        NodeToRootPath(root, target.val, path);
        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for (int i = 0; i < path.size(); i++) {
            if (k - i < 0)
                break;
            kDown(path.get(i), blockNode, k - i, ans);
            blockNode = path.get(i);
        }
        return ans;

    }

    // all node k distance away optimized
    // it is not static
    public int distanceK_01(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            kDown(root, null, k, ans);
            return 1;
        }
        int ld = distanceK_01(root.left, target, k, ans);
        if (ld != -1) {
            kDown(root, root.left, k - ld, ans);
            return ld + 1;
        }
        int rd = distanceK_01(root.right, target, k, ans);
        if (rd != -1) {
            kDown(root, root.right, k - rd, ans);
            return rd + 1;
        }
        return -1;
    }

    public List<Integer> distanceK_01(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        distanceK_01(root, target, k);
        return ans;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    // definations________________________________________________________________________
    // perfect tree- : It is a tree in which every node either has 2 childs or no
    /////////////////////////////////////////////////////////////////////////////////////// child
    // complete tree-: it is a tree in which each node either has 0,1 or 2 childs
    /////////////////////////////////////////////////////////////////////////////////////// but
    /////////////////////////////////////////////////////////////////////////////////////// it
    /////////////////////////////////////////////////////////////////////////////////////// is
    /////////////////////////////////////////////////////////////////////////////////////// left
    /////////////////////////////////////////////////////////////////////////////////////// oriented
    /////////////////////////////////////////////////////////////////////////////////////// i.e
    /////////////////////////////////////////////////////////////////////////////////////// fill
    /////////////////////////////////////////////////////////////////////////////////////// node
    /////////////////////////////////////////////////////////////////////////////////////// from
    /////////////////////////////////////////////////////////////////////////////////////// left
    /////////////////////////////////////////////////////////////////////////////////////// to
    /////////////////////////////////////////////////////////////////////////////////////// right
    /////////////////////////////////////////////////////////////////////////////////////// .
    //

    // burning tree question (Important)

    public void kDown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode) {
            return;
        }
        if (time == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(time).add(root.val);
        kDown(root.left, time + 1, blockNode, ans);
        kDown(root.right, time + 1, blockNode, ans);
    }

    public int burningTree(TreeNode root, TreeNode target, ArrayList<ArrayList<Integer>> ans) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            kDown(root, 0, null, ans);
            return 1;
        }
        int ld = burningTree(root.left, target, ans);
        if (ld != -1) {
            kDown(root, ld, root.left, ans);
            return ld + 1;
        }
        int rd = burningTree(root.right, target, ans);
        if (rd != -1) {
            kDown(root, ld, root.right, ans);
            return rd + 1;
        }
        return -1;
    }

    public void burningTree(TreeNode root, TreeNode target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);

    }

    // burning tree with water question (Important)

    public void kDownWithWater(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> waterList) {
        if (root == null || root == blockNode || waterList.contains(root.val)) {
            return;
        }
        if (time == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(time).add(root.val);
        kDownWithWater(root.left, time + 1, blockNode, ans, waterList);
        kDownWithWater(root.right, time + 1, blockNode, ans, waterList);
    }

    // returning -1 means did we get the target node
    // returning -2 means we found a waterNode in between root to node path and fire
    // will not reacg there

    public int burningTreeWithWater(TreeNode root, TreeNode target, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> waterList) {
        if (root == null) {
            return -1;
        }
        if (root == target) {
            if (!waterList.contains(root.val)) {

                kDownWithWater(root, 0, null, ans, waterList);
                return 1;
            } else {
                return -2;
            }
        }
        int ld = burningTreeWithWater(root.left, target, ans, waterList);
        if (ld > 0) {
            if (!waterList.contains(root.val)) {

                kDownWithWater(root, ld, root.left, ans, waterList);
                return ld + 1;
            } else {
                return -2;
            }
        }
        if (ld == -2) {
            return -2;
        }
        int rd = burningTreeWithWater(root.right, target, ans, waterList);
        if (rd > 0) {
            if (!waterList.contains(root.val)) {

                kDownWithWater(root, ld, root.right, ans, waterList);
                return rd + 1;
            } else {
                return -2;
            }
        }
        if (rd == -2) {
            return -2;
        }
        return -1;
    }

    public void burningTreeWithWater(TreeNode root, TreeNode target, ArrayList<Integer> waterList) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int ele : waterList) {
            set.add(ele);
        }
        burningTreeWithWater(root, target, ans, set);

    }

    // LCA Binary tree

    public boolean lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q, TreeNode LCA) {
        if (root == null) {
            return false;
        }
        boolean self = false;
        if (root == p || root == q) {
            self = true;
        }
        boolean left = lowestCommonAncestor_(root.left, p, q, LCA);
        // if (LCA != null) {
        //     return true;
        // }
        boolean right = lowestCommonAncestor_(root.right, p, q, LCA);

        if ((left && right) || (left && self) || (self && right)) {
            LCA.left = root;
        }
        return left || right || self;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode LCA = new TreeNode(0);
        lowestCommonAncestor_(root, p, q, LCA);
        return LCA.left;
    }

    public static void main(String[] args) {

    }
}
