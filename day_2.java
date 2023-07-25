import java.util.*;
public class day_2 {

    public static void printArr(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();

    }

    public static void print2Arr(int[][] arr) {
        for (int[] a : arr) {
            printArr(a);
        }
        System.out.println();
    }
    /*
     * jump set ( jisme mostly jumps lagti hai or remember ki try karo dp piche se solve kare)
     * 
     * climb stair (leet code question no.70 https://leetcode.com/problems/climbing-stairs/)
     * climb stari with min cost(leeet code question no. 746 https://leetcode.com/problems/min-cost-climbing-stairs/description/)
     * maze path (unique path leet code question no. 62 https://leetcode.com/problems/unique-paths/description/)
     */
   

    // climb stair (leet code question no.70 https://leetcode.com/problems/climbing-stairs/)
    
    public int climbSolver(int n ,int [] dp){
        if(n<=1){
            return dp[n]=1;
        }
        if(dp[n]!=0){
            return dp[n];
        }
        int ekjump=climbSolver(n-1,dp);
        int dojump=climbSolver(n-2,dp);
        return dp[n]=ekjump+dojump;
    }
    public int climbStairs(int n) {
        int [] dp= new int[n+1];
        return(climbSolver(n,dp)); 
            
        }



   //climb stari with min cost(leeet code question no. 746 https://leetcode.com/problems/min-cost-climbing-stairs/description/)

     public int mincostClimbhelper(int n, int[] cost, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }
        if (dp[n] != 0) {
            return dp[n];
        }

        int singlejump = mincostClimbhelper(n - 1, cost, dp);
        int dojump = mincostClimbhelper(n - 2, cost, dp);
        return dp[n] = Math.min(singlejump, dojump) + (n == cost.length ? 0 : cost[n]);

    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        return mincostClimbhelper(n, cost, dp);
    }

    //maze path (unique path leet code question no. 62 https://leetcode.com/problems/unique-paths/description/)
    
    public int mazePath_memo(int sr,int sc,int er,int ec,int[][]dp, int[][] dir){
        if(sr==er && sc==ec){
            return dp[sr][sc]=1;
        }
        if(dp[sr][sc]!=0){
            return dp[sr][sc];
        }
        int count =0;
      for(int[] d : dir){
          int r=sr+d[0];
          int c=sc+d[1];
          if(r>=0 && c>=0 && r<=er && c<=ec){
            count+= mazePath_memo(r,c,er,ec,dp,dir);
          }
      }
      return dp[sr][sc]=count;
    }
    public int mazePath(int m, int n) {
        int[][] dp=new int[m][n];
        int[][] dir = { { 0, 1 }, { 1, 0} };

        return mazePath_memo(0,0,m-1,n-1,dp,dir);
    }


    public static void main(String[] args) {
        //int n=6;
        //System.out.println(climbStairs(n));
        //int[] cost={10,15,20};
       // System.out.println(minCostClimbingStairs(cost));
       //int m=7,n=5;
        // System.out.println(mazePath(m,n));
    }
}