import java.util.*;

public class day_1 {
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
     * set ma divide karne ka group (inclusion exclusion set)
     * friends pairing
     * divide into k subsets
     * count binary strings(jisme ek binary string length nikalni thi of tyoe not ending with 0)
     * decode ways (leetcode 91 )
     * unique path II
     */

    // frienfds pair question
    public static int friendPair_memo(int n, int[] dp) {
        if (n <= 1) {
            return dp[n] = 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        int single = friendPair_memo(n - 1, dp);
        int pairup = friendPair_memo(n - 2, dp) * n - 1;
        int ans = single + pairup;
        return dp[n] = ans;
    }

    public static int friendPair(int n) {
        int[] dp = new int[n + 1];
        return friendPair_memo(n, dp);
    }

    // divide into k subsets
    public static int divideksubset_memo(int n, int k,int[][] dp){
        if(k==1 || n==k){
            return dp[n][k]=1;
        }
        if(dp[n][k]!=0){
            return dp[n][k];
        }
        int selfset=divideksubset_memo(n-1, k-1, dp);
        int pairwithanother=divideksubset_memo(n-1, k, dp)*k;
        return dp[n][k]=selfset+pairwithanother;
    }

    ///tabulization
      public static int divideksubset_tabu(int N, int K,int[][] dp){
        for(int n=1;n<=N;n++){
            for(int k=1;k<=K;k++){
                 if(k==1 || n==k){
                 dp[n][k]=1;
                 continue;
        }
        if(k>n){
            break;
        }
        
        int selfset= dp[n-1][k-1];      //divideksubset_memo(n-1, k-1, dp);
        int pairwithanother= dp[n-1][k]*k;           //divideksubset_memo(n-1, k, dp)*k;
        dp[n][k]=selfset+pairwithanother;
            }
        }
       return dp[N][K];
    }


    public static void dividekSubset(){
        int n=5, k=3;
        int[][] dp=new int[n+1][k+1];
        //System.out.println(divideksubset_memo(n, k, dp));
        System.out.println(divideksubset_tabu(n, k, dp));
        print2Arr(dp);
    }

    // binay count | no. having np consecutive 0's
    public static void countBinary() {
        int n=5;
        
        // int[] dp0 = new int[n + 1];
        // int[] dp1 = new int[n + 1];

        // dp0[1] = 1;
        // dp1[1] = 1;

        // for (int i = 2; i < dp1.length; i++) {
        //     dp1[i] = dp0[i - 1] + dp1[i - 1];
        //     dp0[i] = dp1[i - 1];
        // }
        // System.out.println(dp1[n] + dp0[n]);

        int cozeros = 1;
        int coones = 1;
        for (int i = 2; i < n + 1; i++) {
            int newZeros = coones;
            int newOnes = coones + cozeros;

            cozeros = newZeros;
            coones = newOnes;
        }
        System.out.println(coones + cozeros);
    }



    // decode ways leet code 91(https://leetcode.com/problems/decode-ways/description/)


    public int numDecodings_memo(String s,int n,int[] dp){
        if(n==0){
            return dp[n]=1;
        }
        if(dp[n-1]!=-1){
            return dp[n];
        }
        int count =0;
        if(s.charAt(n-1)>'0'){
            count+= numDecodings_memo(s,n-1,dp);
        }
        if(n>1){
          int num =(s.charAt(n-2)-'0')*10+(s.charAt(n-1)-'0'); 
          if(num<=26 && num>=10){
            count+=numDecodings_memo(s,n-2,dp);
        } 
        }
        
        return dp[n]=count;
    }


   public int numDecodings(String s) {
       int n=s.length();
       int[] dp=new int [n+1];
       Arrays.fill(dp, -1);
       int ans=numDecodings_memo(s,n,dp);
       return ans;
   }


    public static void main(String[] args) {
       // int n = 5;
       //System.out.println(friendPair(n)); 
       dividekSubset();
       
    }
}