import java.util.Arrays;

public class day_7 {
     /*
      * important string questions 
      * Longest Increasing sussequence (leet code 300)
      *longest decreasing sunsequence
      * Longest bitonic sussequence
      * longest bitonic subsequence reverse pattern 
      *maximum sum increasing subsequense
      * maximum sum bitoinc subsequence
      * maximum number of bridges
      * minimum number of deletion to make array sorted
      *
      */

      // longest increasing subsequence ( leetcode 300)

        public int lengthOfLIS(int[] nums) {
            int n=nums.length;
            int[] dp=new int[n];
            int max=0;
            
            for(int i=0;i<n;i++){
                dp[i]=1;
                for(int j=i-1;j>=0;j--){
                    if(nums[j]<nums[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                  
                    }
                }
                      max=Math.max(max,dp[i]);
            }
            return max;
        }



        
      // longest Decreasing Subsequence
      public static int LDS(int[] arr){
        int n=arr.length;
         int[] dp=new int[n];
        int maxLen=0;
        for(int i=n-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<n;j++){
                if(arr[i]>arr[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            maxLen=Math.max(maxLen,dp[i]);
        }
     return  maxLen;
      }

      // longest Bitonic subsequence
     public static int[] LIS_LR(int[] nums) {
            int n=nums.length;
            int[] dp=new int[n];
            for(int i=0;i<n;i++){
                dp[i]=1;
                for(int j=0;j<=i;j++){
                    if(nums[j]<nums[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                  
                    }
                }
                      
            }
            return dp;
        }
       // lis right to left == LDS
        public static int[] LIS_RL(int[] nums){
        int n=nums.length;
         int[] dp=new int[n];
        for(int i=n-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<n;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
     return  dp;
      }
      public static int longestBitonicsubsequence(int[] nums){
        int[] lis=LIS_LR(nums);
        int[] lds=LIS_RL(nums);
        int n=nums.length, maxLen=0;
        for(int i=0;i<nums.length;i++){
            maxLen=Math.max(maxLen,lis[i]+lds[i]-1);
        }
        return maxLen;
      }

      //=======================================================================================================//
     // bitonic subsequence reverse order
     // LDS left to right
      public static int[] LDS_LR(int[] nums) {
            int n=nums.length;
            int[] dp=new int[n];
            for(int i=0;i<n;i++){
                dp[i]=1;
                for(int j=i-1;j>=0;j--){
                    if(nums[j] >nums[i]){
                        dp[i]=Math.max(dp[i],dp[j]+1);
                  
                    }
                }
                      
            }
            return dp;
        }
       // LDS right to left == LIS
        public static int[] LDS_RL(int[] nums){
        int n=nums.length;
         int[] dp=new int[n];
        for(int i=n-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<n;j++){
                if(nums[i]<nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
     return  dp;
      }
      public static int longestBitonicsubsequence_2(int[] nums){
        int[] lds=LDS_LR(nums);
        int[] lis=LDS_RL(nums);
        int n=nums.length, maxLen=0;
        for(int i=0;i<nums.length;i++){
            maxLen=Math.max(maxLen,lis[i]+lds[i]-1);
        }
        return maxLen;
      }


      // maximum icreasing sum Subsequence
      // geeks for geek https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749
      public int maxSumIS(int nums[], int n) {
        int[] dp=new int[n];
        int maxSum=0;
        for(int i=0;i<n;i++){
            dp[i]=nums[i];
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+nums[i]);
              
                }
            }
            maxSum=Math.max(maxSum,dp[i]);
                  
        }
        return maxSum;
    } 

    // maximum sum bitonic subsequence
    // geeks for geek
    public static int maxSumBS(int nums[], int n)
    {
      int[] lis=LIS_LR(nums);
        int[] lds=LIS_RL(nums);
       int maxSum=0;
        for(int i=0;i<n;i++){
            maxSum=Math.max(maxSum,(lis[i]+lds[i])-nums[i]);
        }
        return maxSum;
      }
      
          public static int[] LIS_LR(int[] nums) {
            int n=nums.length;
            int[] dp=new int[n];
            for(int i=0;i<n;i++){
                dp[i]=nums[i];
                for(int j=0;j<=i;j++){
                    if(nums[j]<nums[i]){
                        dp[i]=Math.max(dp[i],dp[j]+nums[i]);
                  
                    }
                }
                      
            }
            return dp;
        }
       // lis right to left == LDS
        public static int[] LIS_RL(int[] nums){
        int n=nums.length;
         int[] dp=new int[n];
        for(int i=n-1;i>=0;i--){
            dp[i]=nums[i];
            for(int j=i+1;j<n;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+nums[i]);
                }
            }
        }
     return  dp;
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
            dp[i]=1;
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
                dp[i]=1;
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
