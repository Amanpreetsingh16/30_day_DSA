import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class day_17 {
    /*
     * vertical order sum (Shadow technique)
     * Diagnol order sum (Shadow technique)
     * vertical order traversal leetcode 987
     */

     public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class ListNode{
        int data=0;
        ListNode next=null; 
        ListNode prev =null;
        ListNode(int data){
            this.data=data;
        }

    }

    // vertical order sum (Shadow technique)

     public void verticalOrderSum(TreeNode root,ListNode node){
        node.data+=root.val;
        if(root.left!=null){
            if(node.prev==null){
                ListNode nnode=new ListNode(0);
                nnode.next=node;
                node.prev=nnode;
            }
                verticalOrderSum(root.left, node.prev);
        
        }
        if(root.right!=null){
            if(node.next==null){
                ListNode nnode=new ListNode(0);
                node.next=nnode;
                nnode.prev=node;
            }
                verticalOrderSum(root.right, node.next);
        }
     }
    public void verticalOrderSum(TreeNode root){
        ListNode curr=new ListNode(0);
        verticalOrderSum(root,curr);
    }

     // diagnol order sum (Shadow technique)

     public void diagnolOrderSum(TreeNode root,ListNode node){
        node.data+=root.val;
        if(root.left!=null){
            if(node.prev==null){
                ListNode nnode=new ListNode(0);
                nnode.next=node;
                node.prev=nnode;
            }
                diagnolOrderSum(root.left, node.prev);
            
        }
        if(root.right!=null){
            
                diagnolOrderSum(root.right, node);
            
        }
     }
    public void diagnolOrderSum(TreeNode root){
        ListNode curr=new ListNode(0);
        diagnolOrderSum(root,curr);
    }



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
        int level=0;

        vPair(TreeNode node, int vl) {
           this(node,vl,0);
        }
         vPair(TreeNode node, int vl,int level) {
            this.node = node;
            this.vl = vl;
            this.level=level;
        }
    }

  // leet code 987
  // Verticsl order traversal
     public List<List<Integer>> verticalOrderTraversal(TreeNode root) {
        PriorityQueue<vPair> que = new PriorityQueue<>((a,b)->{
            if(a.vl != b.vl){
                return a.vl-b.vl;
            }else{
                return a.node.val-b.node.val;
            }
        });
        PriorityQueue<vPair> childque = new PriorityQueue<>((a,b)->{
            if(a.vl != b.vl){
                return a.vl-b.vl;
            }else{
                return a.node.val-b.node.val;
            }
        });
        List<List<Integer>> ans = new ArrayList<>();
        int[] minmax = new int[2];
        widthOfShadow(root, 0, minmax);

        int len = minmax[1] - minmax[0] + 1;

        que.add(new vPair(root, Math.abs(minmax[0])));
        for (int i = 0; i < len; i++) {
            ans.add(new ArrayList<>());
        }
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.remove();
                ans.get(rp.vl).add(rp.node.val);
                if (rp.node.left != null) {
                    childque.add(new vPair(rp.node.left, rp.vl - 1));
                }
                if (rp.node.right != null) {
                    childque.add(new vPair(rp.node.right, rp.vl + 1));
                }
            }
            PriorityQueue<vPair> temp= que;
            que=childque;
            childque=temp;

        }
        return ans;

    }

    // Second approch of level order traversal using one Priority queue
    
    public List<List<Integer>> verticalOrderTraversal_II(TreeNode root) {
        PriorityQueue<vPair> que = new PriorityQueue<>((a,b)->{
            if(a.level!=b.level){
                return a.level-b.level;
            }
            else if(a.vl != b.vl){
                return a.vl-b.vl;
            }else{
                return a.node.val-b.node.val;
            }
        });
     
        List<List<Integer>> ans = new ArrayList<>();
        int[] minmax = new int[2];
        widthOfShadow(root, 0, minmax);

        int len = minmax[1] - minmax[0] + 1;

        que.add(new vPair(root, Math.abs(minmax[0]),0));
        for (int i = 0; i < len; i++) {
            ans.add(new ArrayList<>());
        }
        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                vPair rp = que.remove();
                int level=rp.level;
                ans.get(rp.vl).add(rp.node.val);
                if (rp.node.left != null) {
                    que.add(new vPair(rp.node.left, rp.vl - 1, level+1));
                }
                if (rp.node.right != null) {
                    que.add(new vPair(rp.node.right, rp.vl + 1, level+1));
                }
            }

        }
        return ans;

    }

}
