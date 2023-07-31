import java.util.Arrays;

public class day_5{
/*
 * string set questions 
 * Max Dot Product (leet code 1458)
 * uncrossed lines (leet code 1035)
 *  Edit distance  (leet code 72)
 *  Delete Operation for Two Strings (leetcode 583)
 * Minimum number of deletions and insertions(geek for geek)
 * Distinct subsequence (leet code 115)
 */
// leetcode 1458 ( https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/)

//max dot product question ( based on longest common subsequence yaad rahk ki 3 call lagi thi bot yes, 1yes other no, 1no other yes, )

 public int maximum(int... arr){
        int max=arr[0];
        for(int ele : arr){
            max=Math.max(max,ele);
        }
        return max;
    }
    public int maxdotProducr_Memo(int[] nums1,int n,int[] nums2,int m,int[][] dp){
        if(n==0 || m==0){
            return dp[n][m]=-(int)1e8;

        }
        if(dp[n][m]!=-(int)1e9){
            return dp[n][m];
        }
        int val=nums1[n-1]*nums2[m-1];
        int acceptboth= maxdotProducr_Memo(nums1,n-1,nums2,m-1,dp)+ val;
        int a=maxdotProducr_Memo(nums1,n,nums2,m-1,dp);
        int b=maxdotProducr_Memo(nums1,n-1,nums2,m,dp);
        return dp[n][m]=maximum(acceptboth,val,a,b);
    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int[][] dp=new int[n+1][m+1];
        for(int[] d : dp){
            Arrays.fill(d,-(int)1e9);
        }
        return (maxdotProducr_Memo(nums1,n,nums2,m,dp));
    }

/// uncrossed lines (leet code 1035)
// https://leetcode.com/problems/uncrossed-lines/description/

public int  maxUncrossedLines_Memo(int[] nums1,int n,int[] nums2,int m,int[][] dp){
    if(n==0 || m==0){
        return dp[n][m]=0;

    }
    if(dp[n][m]!=-1){
        return dp[n][m];
    }
    if(nums1[n-1]==nums2[m-1]){
        return dp[n][m]=maxUncrossedLines_Memo(nums1,n-1,nums2,m-1,dp)+1;
    }else{
        return dp[n][m]=Math.max(maxUncrossedLines_Memo(nums1,n,nums2,m-1,dp),maxUncrossedLines_Memo(nums1,n-1,nums2,m,dp));
    }
    
}
public int  maxUncrossedLines(int[] nums1, int[] nums2) {
    int n=nums1.length;
    int m=nums2.length;
    int[][] dp=new int[n+1][m+1];
    for(int[] d : dp){
        Arrays.fill(d,-1);
    }
    return ( maxUncrossedLines_Memo(nums1,n,nums2,m,dp));
}

// leetcode 72 Edit distance
//https://leetcode.com/problems/edit-distance/description/

public int minDistance_memo(String word1,int n,String word2,int m,int[][] dp){
    if(n==0){
        return dp[n][m]=m;
    }else if(m==0){
        return dp[n][m]=n;
    }
    if(dp[n][m]!=-1){
        return dp[n][m];

    }
    if(word1.charAt(n-1)==word2.charAt(m-1)){
        return dp[n][m]=minDistance_memo(word1,n-1,word2,m-1,dp);
    }
    int insertcall=minDistance_memo(word1,n,word2,m-1,dp);
    int deletecall=minDistance_memo(word1,n-1,word2,m,dp);
    int replacecall=minDistance_memo(word1,n-1,word2,m-1,dp);
    return dp[n][m]=Math.min(Math.min(insertcall,deletecall),replacecall)+1;
}
public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[][] dp=new int[n+1][m+1];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }
        return( minDistance_memo(word1,n,word2,m,dp));
}

// leet code 583. Delete Operation for Two Strings
// https://leetcode.com/problems/delete-operation-for-two-strings/description/

public int minDistance2_Memo(String word1,int n,String word2,int m,int[][] dp){
    if(n==0){
        return dp[n][m]=m;
    }else if(m==0){
        return dp[n][m]=n;
    }
    if(dp[n][m]!=-1){
        return dp[n][m];

    }
    if(word1.charAt(n-1)==word2.charAt(m-1)){
        return dp[n][m]=minDistance_memo(word1,n-1,word2,m-1,dp);
    }
    int deletefrom1st=minDistance_memo(word1,n-1,word2,m,dp);
    int deletefrom2nd=minDistance_memo(word1,n,word2,m-1,dp);
    return dp[n][m]=Math.min(deletefrom1st,deletefrom2nd)+1;
}

public int minDistance2(String word1, String word2) {
    int n=word1.length();
        int m=word2.length();
        int[][] dp=new int[n+1][m+1];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }
        return( minDistance_memo(word1,n,word2,m,dp));
}

//geegforgeek question
// Minimum number of deletions and insertions
public int minOperations_Memo(String str1, int n, String str2, int m, int[][] dp){
    if(n==0){
        return dp[n][m]=m;
    }else if(m==0){
        return dp[n][m]=n;
    }
    if(dp[n][m]!=-1){
        return dp[n][m];
    }
    if(str1.charAt(n-1)==str2.charAt(m-1)){
        return dp[n][m]=minOperations_Memo(str1,n-1,str2,m-1,dp);
    }
    int insert=minOperations_Memo(str1,n,str2,m-1,dp);
    int delete=minOperations_Memo(str1,n-1,str2,m,dp);
    return dp[n][m]=Math.min(insert,delete)+1;
}    
public int minOperations(String str1, String str2) 
{ 
    // Your code goes here
    int n=str1.length();
        int m=str2.length();
        int[][] dp=new int[n+1][m+1];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }
        return( minOperations_Memo(str1,n,str2,m,dp));
    
} 

// Distinct subsequence( leet code 115)
//https://leetcode.com/problems/distinct-subsequences/

public int numDistinct_Memo(String s, String t,int n,int m,int[][] dp){
    if(n==0 || m==0 || n<m){
        return dp[n][m]=(m==0 ? 1 :0);
    }
    if(dp[n][m]!=-1){
        return dp[n][m];
    }
    int a=numDistinct_Memo(s,t,n-1,m-1,dp);
    int b=numDistinct_Memo(s,t,n-1,m,dp);
    if(s.charAt(n-1)==t.charAt(m-1)){
        return dp[n][m]=a+b;
    }else{
        return dp[n][m]=b;
    }

}
// 115 tabulization
public int numDistinct_Dp(String s, String t,int N,int M,int[][] dp){
    for(int n=0;n<=N;n++){
        for(int m=0;m<=M;m++){
            if( m==0 || n<m){
       dp[n][m]=(m==0 ? 1 :0);
       continue;
    }
  
    int a= dp[n-1][m-1]; //numDistinct_Memo(s,t,n-1,m-1,dp);
    int b= dp[n-1][m];//numDistinct_Memo(s,t,n-1,m,dp);
    if(s.charAt(n-1)==t.charAt(m-1)){
        dp[n][m]=a+b;
    }else{
         dp[n][m]=b;
    }
        }
    }
    return dp[N][M];
}
public int numDistinct(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[][] dp=new int[n+1][m+1];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }
        return numDistinct_Memo(s,t,n,m,dp);
}
    public static void main(String[] args){
        
    }
}