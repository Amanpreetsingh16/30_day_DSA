import java.util.Arrays;

public class day_7 {
     /*
      * important string questions 
      * Longest Increasing sussequence (leet code 300)
      * maximum number of bridges
      * minimum number of deletion to make array sorted
      *
      */

      // longest increasing subsequence ( leetcode 300)

        public int lengthOfLIS(int[] nums) {
            int n=nums.length;
            int[] dp=new int[n];
            Arrays.fill(dp,1);
            int max=0;
            
            for(int i=0;i<n;i++){
                for(int j=0;j<=i;j++){
                    if(nums[j]<nums[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                  
                    }
                }
                      max=Math.max(max,dp[i]);
            }
            return max;
        }

     // maximum number of bridges
      public static int maximumBridges(int[][] points){
        int n=points.length;
        Arrays.sort(points, (a,b)->{
            return a[0]-b[0];
        });
        int[] dp=new int[n];
        int maxLen=0;
        for(int i=0;i<n;i++){
            for(int j=i-1;j>=0;j--){
                if(points[i][1]>points[j][1]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxLen=Math.max(maxLen,dp[i]);
        }
     return  maxLen;
      }

      // minimum number of deletion to make array sorted
     public static int mimdeletion(int[] arr){
         int n=arr.length;
            int[] dp=new int[n];
            int max=0;
            for(int i=0;i<n;i++){
                for(int j=i-1;j>=0;j--){
                    if(arr[i]>=arr[j]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                  
                    }
                }
                      max=Math.max(max,dp[i]);
            }
            return n- max;
     }
     
    public static void main(String[] args){
      
    }
}
