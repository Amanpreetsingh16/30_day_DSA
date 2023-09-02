import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.swing.tree.TreeNode;

public class day_9 {
    /*
     * cut set 
     * Matrix chain multiplication
     * min max expression
     * baloon burst (leetcode 312)
     * optimal binary search tree
     * unique binary search tree II (leetcode 95)
     * 1039. Minimum Score Triangulation of Polygon
     * word break leetcode 139 
     */
    // geeksforgeek matrix chain multiplication
    static int mcm_memo(int[] arr,int si,int ei, int[][] dp){
        if(si+1==ei){
            return dp[si][ei]=0;
        }
        if(dp[si][ei]!=-1){
            return dp[si][ei];
        }
        int minRes=(int)1e10;
        for(int cut =si+1;cut<ei;cut++){
            int leftRes=mcm_memo(arr,si,cut,dp);
            int rightRes=mcm_memo(arr,cut,ei,dp);
            int res=leftRes+arr[si]*arr[cut]*arr[ei]+rightRes;
            if(res<minRes){
                minRes=res;
            }
        }
        return dp[si][ei]=minRes;
    }
    static int matrixMultiplication(int N, int arr[])
    {
        int[][] dp=new int[N][N];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }
        int ans=mcm_memo(arr,0,N-1,dp);
        return ans;
    }   

    // minimum maximum value expression 
    public static class MinMax{
        int minVal=(int)1e9;
        int maxVal=-(int)1e9;
        public MinMax(){

        }
        public  MinMax(int minVal, int maxVal){
            this.minVal=minVal;
            this.maxVal=maxVal;
        }
    }
    public static MinMax evaluation(MinMax lv,MinMax rv,char operator){
       
        if(operator=='+'){
            return new MinMax(lv.minVal+rv.minVal,lv.maxVal+rv.maxVal);
        }else{
            return new MinMax(lv.minVal*rv.minVal,lv.maxVal*rv.maxVal);
        }
    }
    public static MinMax minMaxEval(String s, int si,int ei,MinMax[][] dp){
        if(si==ei){
            int val=s.charAt(si)-'0';
            return new MinMax(val, val);
        }
        if(dp[si][ei]!=null){
            return dp[si][ei];
        }
        MinMax myRes=new MinMax();
        for(int cut =si+1;cut<ei;cut+=2){
            MinMax leftVal=minMaxEval(s, si, cut-1, dp);
            MinMax rightVal=minMaxEval(s, cut+1, ei, dp);
            MinMax eval=evaluation(leftVal, rightVal,s.charAt(cut));
            myRes.minVal=Math.min(myRes.minVal,eval.minVal);
            myRes.maxVal=Math.max(myRes.maxVal,eval.maxVal);
        }
        return dp[si][ei]=myRes;
    }
    public static void minMaxeval(){
        String str="1+2*3+4*5";
        int n=str.length();
        MinMax[][] dp=new MinMax[n][n]; 
        MinMax res= minMaxEval(str, 0, n-1, dp);
        System.out.println("minvalue="+res.minVal);
        System.out.println("maxvalue="+res.maxVal);

    }

    // leetcode 312 Ballon Burst
    public int maxCoins(int[] nums, int si,int ei, int[][] dp){
        if(dp[si][ei]!=0){
            return dp[si][ei];
        }
        int lval=si==0 ? 1 : nums[si-1];
        int rval=ei==nums.length-1 ? 1 : nums[ei+1];
        int maxVal=0;
        for(int cut=si;cut<=ei; cut++){
            int leftVal=cut==si ? 0 : maxCoins(nums,si,cut-1,dp);
            int rightVal=cut==ei ? 0: maxCoins(nums,cut+1,ei,dp);
            int myval=leftVal+lval*nums[cut]*rval+rightVal;
            maxVal=Math.max(myval,maxVal);
        }
        return dp[si][ei]=maxVal;
    }
    public int maxCoins(int[] nums) {
        int n=nums.length;
        int[][] dp=new int[n][n];
        return (maxCoins(nums,0,n-1,dp));
    }

    // optimal binary search tree
    // geeks for geek
    // static int sumOfFreq(int i, int j, int[] freq){
    //     int sum=0;
    //     while(i<=j){
    //         sum+=freq[i];
    //         i++;
    //     }
    //     return sum;
    // }
    static int optimalSearchTree_memo(int keys[], int freq[], int si, int ei,int[][] dp)
    {
        if(dp[si][ei]!=-1){
            return dp[si][ei];
        }
        int minVal=(int)1e9;
        int sum=0;
        for(int cut=si;cut<=ei;cut++){
            int leftRes=cut==si ? 0 : optimalSearchTree_memo(keys,freq,si,cut-1,dp);
            int rightRes=cut==ei ? 0 : optimalSearchTree_memo(keys,freq,cut+1,ei,dp);
            sum+=freq[cut];
            // this sumOfFreq call will make code complexity n^4 
            //int myRes=leftRes+ sumOfFreq(si,ei,freq) +rightRes; 

            // to make it n^3 we will make a sum variable in code
            int myRes=leftRes +rightRes; 
            minVal=Math.min(minVal,myRes);
        }
        return dp[si][ei]=minVal + sum;
        
    }   
    static int optimalSearchTree(int keys[], int freq[], int n)
    {
        int[][] dp=new int[n][n];
        for(int[] d: dp){
            Arrays.fill(d,-1);
        }
        
        return optimalSearchTree_memo(keys,freq,0,n-1,dp);
    }

    // leetcode 95 
    // Unique Binary search tree II
    
  //Definition for a binary tree node.
 public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 
class Solution {
    public void generateAllBst(int num,  List<TreeNode> left, List<TreeNode> right,  List<TreeNode> ans){
        if(left.size()!=0 && right.size()!=0){
            for(int i=0; i<left.size();i++){
                for(int j=0;j<right.size();j++){
                     TreeNode root=new TreeNode(num);
                     root.left=left.get(i);
                     root.right=right.get(j);
                     ans.add(root);
                }
            }
        }else if(left.size()!=0){
            for(int i=0;i<left.size();i++){
                     TreeNode root=new TreeNode(num);
                     root.left=left.get(i);
                     ans.add(root);
                }
        }else if(right.size()!=0){
                 for(int j=0;j<right.size();j++){
                     TreeNode root=new TreeNode(num);
                     root.right=right.get(j);
                     ans.add(root);
                }
        }else{
            TreeNode root=new TreeNode(num);
            ans.add(root);
        }
    }
    public List<TreeNode> generateTrees(int si, int ei){
        List<TreeNode> ans=new ArrayList<>();
        for(int cut=si;cut<=ei;cut++){
            List<TreeNode> leftList=generateTrees(si,cut-1);
            List<TreeNode> rightList=generateTrees(cut+1,ei);
            generateAllBst(cut,leftList,rightList,ans);
        }
        return ans;
    }
    public List<TreeNode> generateTrees(int n) {
       return generateTrees(1,n);
    }
}

// 1039. Minimum Score Triangulation of Polygon
public int minScoreTriangulation(int[] values, int si, int ei, int[][] dp){
    if(ei-si<=1){
        return dp[si][ei]=0;
    }
    if(dp[si][ei]!=-1){
        return dp[si][ei];

    }
    int minRes=(int)1e9;
    for(int cut=si+1;cut<ei;cut++){
        int leftRes=minScoreTriangulation(values,si,cut,dp);
         int rightRes=minScoreTriangulation(values,cut,ei,dp);
         int myRes=leftRes +values[si]*values[cut]*values[ei]+rightRes;
         minRes=Math.min(myRes,minRes);
    }
    return dp[si][ei]=minRes;
}
public int minScoreTriangulation(int[] values) {
   int n=values.length;
   int[][] dp =new int[n][n];
   for(int[] d : dp){
       Arrays.fill(d,-1);
   }
   return minScoreTriangulation(values,0,n-1,dp);
}

// leetcode 139
// word break
 public boolean wordBreak(String s, List<String> wordDict) {
        int n=s.length();
        HashSet<String> words=new HashSet<>();
        int len=0;
        for(String str: wordDict){
            len=Math.max(str.length(), len);
            words.add(str);
        }
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        for(int i=0;i<n;i++){
            if(!dp[i]){
                continue;
            }
            for(int l=1;l<=len && i+l<=n ;l++){
                if(words.contains(s.substring(i,i+l))){
                    dp[i+l]=true;
                }
            }
        }
        return dp[n];
    }

    // word break II 
    //leetcode 140
    public void dfs(String s,int idx,int len,String asf, List<String> ans,HashSet<String> words, boolean[] dp ){
        if(idx==s.length()){
            ans.add(asf.substring(0,asf.length()-1));
            return;
        }
        for(int l=1; l<=len && l+idx<=s.length();l++){
            if(dp[idx+l] && words.contains(s.substring(idx,idx+l))){
                dfs(s,idx+l,len,asf+s.substring(idx,idx+l)+" ",ans,words,dp);
            }
        }
    }
    public List<String> wordBreak_II(String s, List<String> wordDict) {
             int n=s.length();
        HashSet<String> words=new HashSet<>();
        int len=0;
        for(String str: wordDict){
            len=Math.max(str.length(), len);
            words.add(str);
        }
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        for(int i=0;i<n;i++){
            if(!dp[i]){
                continue;
            }
            for(int l=1;l<=len && i+l<=n ;l++){
                if(words.contains(s.substring(i,i+l))){
                    dp[i+l]=true;
                }
            }
        }
       List<String> ans=new ArrayList<>();
       if(!dp[n]){
           return ans;
       }
      dfs(s,0,len,"",ans,words,dp);
      return ans;
    }
     public static void main(String[] args){
     minMaxeval();
     }
}
