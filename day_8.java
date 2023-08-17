import java.util.*;
public class day_8 {
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
 * Target Set 
 * coin change permutation infinite supply
 * coin change combination infinte supply
 */


 // coin change permutation infinite supply
 public static int coinChangepermu_memo(int[] arr, int tar,int[] dp){
    if(tar==0){
        return dp[tar]=1;
    }
    if(dp[tar]!=-1){
        return dp[tar];
    }
    int count=0;
    for(int ele: arr){
        if(tar-ele>=0){
            count+=coinChangepermu_memo(arr, tar-ele, dp);
        }
    }
    return dp[tar]=count;
 }

 // coin change permutation tabuization
 public static int coinChangepermu_Tabu(int[] arr, int Tar, int[] dp ){
    for(int tar=0;tar<=Tar;tar++){
          if(tar==0){
         dp[tar]=1;
         continue;
    }

    int count=0;
    for(int ele: arr){
        if(tar-ele>=0){
            count+= dp[tar-ele];  //coinChangepermu_memo(arr, tar-ele, dp);
        }
    }
        dp[tar]=count;
    }
    return dp[Tar];
 }

 public static int coinChangepermu(int[] arr, int tar){
  int[] dp=new int[tar+1];
  Arrays.fill(dp,-1);
  int ans=coinChangepermu_Tabu(arr, tar, dp);
 printArr(dp);
  return ans;
 }


 /// leetcode 322 coin change
// https://leetcode.com/problems/coin-change/description/
 public int coinChange_memo(int[] arr, int tar, int[] dp) {
    if(tar==0){
        return dp[tar]=0;
    }
    if(dp[tar]!=(int)1e9){
        return dp[tar];
    }
    int mincount=(int)1e8;
    for(int ele : arr){
        if(tar-ele>=0){
            mincount=Math.min(mincount, coinChange_memo(arr,tar-ele,dp)+1);
        }
    }
    return dp[tar]=mincount>=(int)1e8 ? (int)1e8 : mincount;
}
public int coinChange(int[] coins, int amount) {
    int[] dp= new int[amount+1];
    Arrays.fill(dp,(int)1e9);
    int ans=coinChange_memo(coins,amount,dp);
    return ans==(int)1e8 ? -1 : ans;
}


// leetcode 518 coin change II (coin change combination)
// https://leetcode.com/problems/coin-change-ii/description/
public int change_dp(int Amt, int[] coins){
    int[] dp= new int[Amt+1];
     dp[0]=1;
      for(int ele : coins){
       for(int amt=0;amt<=Amt;amt++){
            if(amt-ele>=0){
                dp[amt]+=dp[amt-ele];
            }
        }
    }
    return dp[Amt];
}
public int change(int amount, int[] coins) {
    return (change_dp(amount,coins));
}

// target sum problem 
public static int targetSum_memo(int[] arr, int n, int tar,int[][] dp){
    if(tar==0 || n==0){
        return dp[n][tar]= (tar== 0 ? 1 : 0);
    }
    if(dp[n][tar]!=-1){
        return dp[n][tar];
    }
    int count=0;
    if(tar-arr[n-1]>=0){
        count+=targetSum_memo(arr,n-1,tar-arr[n-1],dp);
        count+=targetSum_memo(arr,n-1,tar,dp);
    }
    return dp[n][tar]=count;
}
public static int targetSum(int[] nums, int target){
    int n=nums.length;
    int[][] dp= new int[n+1][target+1];
    for(int[] d: dp){
        Arrays.fill(d,-1);
    }
    int ans=targetSum_memo(nums,n,target,dp);
    print2Arr(dp);
    return ans;
    
}
// geeksfor geek \
// target sum problem
static Boolean isSubsetSum(int N, int arr[], int Sum){
    // code here
    boolean[][] dp=new boolean[N+1][Sum+1];
    for(int n=0;n<=N; n++){
        for(int sum=0;sum<=Sum;sum++){
            if(sum==0 || n==0){
             if(sum==0){
                 dp[n][sum]=true;
             }else{
                 dp[n][sum]=false;
             }
             continue;
            }
            if(dp[n-1][sum]==true){
                dp[n][sum]=true;
            }else{
                if(sum-arr[n-1]>=0){
                    if(dp[n-1][sum-arr[n-1]]==true){
                        dp[n][sum]=true;
                    }
                }
            }
        }
    }
    return dp[N][Sum];
}

// leetcode 377
// combination sum IV
public int combinationSum4(int[] nums, int target) {
    int[] dp= new int[target+1];
      dp[0]=1;
       for(int i=0;i<=target;i++){
        for(int ele : nums){
             if(i-ele>=0){
                 dp[i]+=dp[i-ele];
             }
         }
     }
     return dp[target];
 }


 // leetcode 416 partition int equal sum
 public int canPartition(int[] nums, int n, int tar, int[][] dp){
    if(n==0 || tar==0){
        return dp[n][tar]=tar==0 ? 1: 0;
    }
    if(dp[n][tar]!=-1){
        return dp[n][tar];
    }
    boolean res= canPartition(nums,n-1,tar,dp)==1;

    if(tar-nums[n-1]>=0){
    res=res || canPartition(nums,n-1,tar-nums[n-1],dp)==1;
   
    }
    
    return dp[n][tar]=(res ? 1 : 0);
}
public boolean canPartition(int[] nums) {
    int n=nums.length,sum=0;
    for(int ele : nums){
        sum+=ele;
    }
    if(n==0 || sum % 2 !=0){
        return false;
    }
    int[][] dp= new int[n+1][(sum/2)+1];
    for(int[] d : dp){
        Arrays.fill(d,-1);
    }
    return canPartition(nums,n,sum/2,dp)==1;

}

// leet code target sum expression
    public static void main(String[] args){
      int[] arr={2,3,5,7};
      int tar=10;
      //System.out.println(coinChangepermu(arr, tar));
      System.out.println(targetSum(arr,tar));

    }
}
