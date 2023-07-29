import java.util.Arrays;

public class day_4{

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
     * String Set 
     * longest palindromic susequence (leetcode 516 )
     * longest pallindromic susequence(string nikali)
     * longest common susequence (leet code 1143)
     * 
     */
   
     //longest palindromic susequence (leetcode 516 )
     //https://leetcode.com/problems/longest-palindromic-subsequence/description/


    public int lpss(String s,int si,int ei,int[][] dp){
        if(si>=ei){
            return dp[si][ei]=(si==ei)?1 :0;

        }
        if(dp[si][ei]!=0){
            return dp[si][ei];
        }
        if(s.charAt(si)!=s.charAt(ei)){
            int leftexcl=lpss(s,si+1,ei,dp);
            int rightexcl=lpss(s,si,ei-1,dp);
            dp[si][ei]=Math.max(leftexcl,rightexcl);
        }else{
            dp[si][ei]=lpss(s,si+1,ei-1,dp)+2;
        }
        return dp[si][ei];
    }

    public static int lpss_tabu(String s ,int SI,int EI,int [][] dp){
        int n=s.length();
        for(int gap=0;gap<n;gap++){
          for(int si=0, ei=gap;ei<n;si++,ei++){
                if(si>=ei){
             dp[si][ei]=(si==ei)?1 :0;
             continue;

        }
       
        if(s.charAt(si)!=s.charAt(ei)){
            dp[si][ei]=Math.max(dp[si+1][ei],dp[si][ei-1]);
        }else{
            dp[si][ei]=dp[si+1][ei-1]+2;
                }
            }
        }
        return dp[SI][EI];
    }
    public static int longestPalindromeSubseq(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        //return lpss(s,0,n-1,dp);
     
        int ans=lpss_tabu(s,0,n-1,dp);
        print2Arr(dp);
        return ans;
    }

// longest pallindrom subsequence using reverse engineering( longest subsequence nikalte hai with the help of dp filled)

    public static String lpss_reverseEngineering(String s,int si,int ei,int [][] dp){
        if(si>=ei){
            return si==ei ? (  s.charAt(si)+ ""): " ";
        }
        if(s.charAt(si)==s.charAt(ei)){
            return  (s.charAt(si)+lpss_reverseEngineering(s,si+1,ei-1,dp)+s.charAt(ei));
        }else if(dp[si+1][ei]>dp[si][ei-1]){
            return lpss_reverseEngineering(s,si+1,ei,dp);
        }else{
            return lpss_reverseEngineering(s,si,ei-1,dp);
        }

    }

    public static String longestPalindrome(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];
        int ans=lpss_tabu(s,0,n-1,dp);
        return( lpss_reverseEngineering(s,0,n-1,dp));

    }
 

    // longest common subsequence {leetcode 1143}
    // https://leetcode.com/problems/longest-common-subsequence/submissions/

     public int lcs(String str1, int n, String str2, int m, int[][] dp ){
        if(n==0 || m==0){
            return dp[n][m]=0;
        }
        if(dp[n][m]!=-1){
            return dp[n][m];
        }

        if(str1.charAt(n-1)== str2.charAt(m-1)){
            return dp[n][m]=lcs(str1,n-1,str2,m-1,dp)+1;
        }else{
            return dp[n][m]=Math.max(lcs(str1,n-1,str2,m,dp),lcs(str1,n,str2,m-1,dp));
        }


    }
   
    // lcs dp ,ethod
    public static int lcs_dp(String str1, int N, String str2, int M, int[][] dp){
        for(int n=0;n<=N;n++){
            for(int m=0;m<=M;m++){
                 if(n==0 || m==0){
                    dp[n][m]=0;
                    continue;
        }
       

        if(str1.charAt(n-1)== str2.charAt(m-1)){
           dp[n][m]= dp[n-1][m-1]+1; //lcs_dp(str1,n-1,str2,m-1,dp)+1;
        }else{
            dp[n][m]=Math.max(dp[n-1][m],dp[n][m-1]);     //Math.max(lcs_dp(str1,n-1,str2,m,dp),lcs_dp(str1,n,str2,m-1,dp));
        }

            }

        }
        return dp[N][M];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n=text1.length();
        int m=text2.length();
        int [][] dp=new int[n+1][m+1];
        for(int[] d: dp){
            Arrays.fill(d,-1);
        }
        return(lcs(text1,n,text2,m,dp));
    }
    /// longest pallindromic sustring( leet code 5)

    public static String longestpalliondromSubstring(String s){
            int n=s.length();
            boolean dp[][]=new boolean[n][n];
            int len=0,si=0;
            for(int gap=0;gap<n;gap++){
                for(int i=0,j=gap;j<n;i++,j++){
                    if(gap==0){
                        dp[i][j]=true;
                    }else if(gap==1 && s.charAt(i)==s.charAt(j)){
                        dp[i][j]=true;
                    }else{
                        dp[i][j]=s.charAt(i)==s.charAt(j) && dp[i+1][j-1];
                    }
                    if(dp[i][j] && j-i+1>len){
                        len=j-i+1;
                        si=i;
                    }
                }
            }
            return s.substring(si, si+len);
    }


    // longest common SubString

   public static int longestCommonSubstr(String S1, String S2, int N, int M){
        int[][] dp=new int[N+1][M+1];
        int maxLen=0;
        for(int n=0;n<=N;n++){
            for(int m=0;m<=M;m++){
                if(n==0 || m==0){
                    dp[n][m]=0;
                }
                else if(S1.charAt(n-1)==S2.charAt(m-1)){
                    
                dp[n][m]=dp[n-1][m-1]+1;
                maxLen=Math.max(dp[n][m],maxLen);
            }else{
                dp[n][m]=0;
            }
         }
       }
       return maxLen;
    }
    public static void main(String[] args){
        String s="bbbab";
        System.out.println(longestPalindromeSubseq(s));
        System.out.println(longestPalindrome(s));
    }
}