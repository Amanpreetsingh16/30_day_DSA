import java.util.*;

public class day_1{
    public static void printArr(int[] arr){
        for(int ele : arr){
            System.out.print(ele + " ");
        }
        System.out.println();
        
    }
    public static void print2Arr(int[][] arr){
        for (int[] a : arr) {
            printArr(a);
        }
        System.out.println();
    }



    //frienfds pair question
    public static int friendPair_memo(int n,int[] dp) {
        if(n<=1){
            return dp[n]=1;
        }
        if(dp[n]!=0){
            return dp[n];
        }
         int single=friendPair_memo(n-1, dp);
         int pairup=friendPair_memo(n-2, dp)*n-1; 
         int ans=single+pairup;
         return dp[n]=ans;
    }
   public static int friendPair(int n){
    int[] dp=new int[n+1];
    return friendPair_memo(n, dp);
   } 
  public static void main(String[] args) {
       int n=5;
       friendPair(n);
    }
}