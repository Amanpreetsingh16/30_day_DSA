import java.util.Arrays;

public class day_6 {

    /*\
     * include Exclude set
     * A=B+C subsequence geekforgeeks
     * geeks for geek count pallindrom subsequence
     * Wild card MAtch leetcode 44
     * leetcode 132 pallindromic partition
     * /regular expression matching leet code 10
     */


     // A=B+C subsequence
     public int fun(String s)
     {
         // Write your code here
         int mod=(int)1e9+7;
         long a=0;
         long ab=0;
         long abc=0;
         for(int i=0;i<s.length();i++){
             char ch=s.charAt(i);
             if(ch=='a'){
                 a=(2*a+1%mod)%mod;
             }else if(ch=='b'){
                 ab=(2*ab+a%mod)%mod;
             }else{
                 abc=(2*abc+ab%mod)%mod;
             }
         }
         return (int) (abc%mod);
     }

// geeks for geek count pallindrom subsequence
     long countPs_memo(String str,int i ,int j,long[][] dp){
        if(i>=j){
            return dp[i][j]=(i==j ? 1 :0);
            
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int mod=(int) 1e9+7;
        long common=countPs_memo(str,i+1,j-1,dp);
        long excludingfirst=countPs_memo(str,i+1,j,dp);
        long excludinglast=countPs_memo(str,i,j-1,dp);
        if(str.charAt(i)==str.charAt(j)){
            dp[i][j]=(excludingfirst+excludinglast +1)%mod;
        }else{
            dp[i][j]=(excludingfirst+excludinglast-common+mod)%mod;
        }
        return dp[i][j];
    }
    long countPS(String str)
    {
        int n=str.length();
       long[][] dp=new long[n][n];
            for(long[] d : dp){
                Arrays.fill(d,-1);
            }
            return (countPs_memo(str,0,n-1,dp));
    }
     
// Wild card MAtch leetcode 44
// https://leetcode.com/problems/wildcard-matching/description/

public String removeStar(String str){
    if(str.length()==0){
        return "";

    }
    StringBuilder sb=new StringBuilder();
    sb.append(str.charAt(0));
    int i=1;
    while(i<str.length()){
        while(i<str.length() && sb.charAt(sb.length()-1)=='*' && str.charAt(i)=='*'){
            i++;
        }
        if(i<str.length()){
            sb.append(str.charAt(i));
            i++;
        }
    }
    return sb.toString();
}
public int isMatch_memo(String s,int n,String p,int m, int[][] dp){
    if(n==0 || m==0){
        if(n==0 && m==0 ){
            return dp[n][m]=1;
        }else if(m==1 && p.charAt(m-1)=='*'){
            return dp[n][m]=1;
        }else{
            return dp[n][m]=0;
        }
    }
    if(dp[n][m]!=-1){
        return dp[n][m];
    }
    char ch1=s.charAt(n-1);
    char ch2=p.charAt(m-1);
    if(ch1==ch2 || ch2=='?'){
        return dp[n][m]= isMatch_memo(s,n-1,p,m-1,dp);
    }else if(ch2=='*'){
        boolean res=false;
        res=res || isMatch_memo(s,n-1,p,m,dp)==1;
        res=res || isMatch_memo(s,n,p,m-1,dp)==1;
        return dp[n][m]=res ? 1 :0;
    }else{
        return dp[n][m]=0;
    }
}
public boolean isMatch(String s, String p) {
    p=removeStar(p);
    int n=s.length();
    int m=p.length();
    int [][] dp=new int[n+1][m+1];
    for(int[] d : dp){
        Arrays.fill(d,-1);
    }
    return(isMatch_memo(s,n,p,m,dp)==1);
}

 //leetcode 132 pallindromic partition
//https://leetcode.com/problems/palindrome-partitioning-ii/description/
 public int minCut_memo(String s,int si,boolean[][] ispallindrom,int[] dp){
    if(ispallindrom[si][s.length()-1]){
        return dp[si]=0;
    }
    if(dp[si]!=-1){
        return dp[si];
    }
    int minAns=(int)1e9;
    for(int cut=si;cut<s.length();cut++){
        if(ispallindrom[si][cut]){
            minAns=Math.min(minAns,minCut_memo(s,cut+1,ispallindrom,dp)+1);
        }
    }
    return dp[si]=minAns;
}
public int minCut(String s) {
  int n = s.length();
    boolean ispallindrom[][] = new boolean[n][n];
    for (int gap = 0; gap < n; gap++) {
        for (int i = 0, j = gap; j < n; i++, j++) {
            if (gap == 0) {
                ispallindrom[i][j] = true;
            } else if (gap == 1 && s.charAt(i) == s.charAt(j)) {
               ispallindrom[i][j] = true;
            } else {
                ispallindrom[i][j] = s.charAt(i) == s.charAt(j) && ispallindrom[i + 1][j - 1];
            }  
      }
    }
    int [] dp=new int[n];
    Arrays.fill(dp,-1);
    return(minCut_memo(s,0,ispallindrom,dp));
}

// regular expression matching leet code 10
// https://leetcode.com/problems/regular-expression-matching/description/
public String removeStar(String str){
    if(str.length()==0){
        return "";

    }
    StringBuilder sb=new StringBuilder();
    sb.append(str.charAt(0));
    int i=1;
    while(i<str.length()){
        while(i<str.length() && sb.charAt(sb.length()-1)=='*' && str.charAt(i)=='*'){
            i++;
        }
        if(i<str.length()){
            sb.append(str.charAt(i));
            i++;
        }
    }
    return sb.toString();
}
public int isMatch_memo(String s,int n,String p,int m, int[][] dp){
    if(n==0 || m==0){
        if(n==0 && m==0 ){
            return dp[n][m]=1;
        }
         if(m==0){
            return dp[n][m]=0;
        }
    }
    if(dp[n][m]!=-1){
        return dp[n][m];
    }
    char ch1=n>0 ? s.charAt(n-1) : '$';
    char ch2=p.charAt(m-1);
    if(ch1!='$' && (ch1==ch2 || ch2=='.')){
        return dp[n][m]= isMatch_memo(s,n-1,p,m-1,dp);
    }else if(ch2=='*'){
        boolean res=false;
        if(m>1 && n>0 &&(p.charAt(m-2)=='.' || s.charAt(n-1)==p.charAt(m-2))){

        res=res || isMatch_memo(s,n-1,p,m,dp)==1;
        }
        res=res || isMatch_memo(s,n,p,m-2,dp)==1;
        return dp[n][m]=res ? 1 :0;
    }else{
        return dp[n][m]=0;
    }
}
public boolean isMatch(String s, String p) {
    p=removeStar(p);
    int n=s.length();
    int m=p.length();
    int [][] dp=new int[n+1][m+1];
    for(int[] d : dp){
        Arrays.fill(d,-1);
    }
    return(isMatch_memo(s,n,p,m,dp)==1);
}

    public static void main(String[] args){

    }
}
