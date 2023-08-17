import java.util.Arrays;

public class day_9 {
    /*
     * cut set 
     * Matrix chain multiplication
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
     public static void main(String[] args){

     }
}
