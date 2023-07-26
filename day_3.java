import java.util.*;
public class  day_3{
  
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

    public static void print2ArrBool(boolean[][] arr){
        for (boolean[] a : arr) {
            for (boolean b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 
     * Set 4 Buy and Sell
        1. One Transaction Allowed
        2. Infinite Transaction Allowed
        3. With Transaction Fee and Infinite Transaction Allowed
        4. With Cool Down and Infinite Transaction Allowed
        5. Two Transaction Allowed
        6. K Transaction Allowed 
    */

    // 1. Buy And Sell Stocks - One Transaction Allowed
    public static int oneTransactionAllowed(int[] arr, int n) {
        int leastPrice = (int)1e9;
        int maxProfit = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < leastPrice){
                leastPrice = arr[i];
            }

            int todaysProfit = arr[i] - leastPrice;
            if(todaysProfit > maxProfit){
                maxProfit = todaysProfit;
            }
        }
        return maxProfit;
    }

    // 2. Infinite Transaction Allowed
    public static int infiniteTransactionAllowed(int[] arr, int n) {
        int bd = 0, sd = 0, profit = 0;

        for (int i = 1; i < arr.length; i++) {
           if(arr[i] >= arr[i - 1]){
            sd++;
           } else {
            profit += arr[sd] - arr[bd];
            bd = sd = i;
           }
        }
        profit += arr[sd] - arr[bd];
        return profit;
    }




    public static void main(String[] args){


    }
}