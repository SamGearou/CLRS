package codeforces;

import java.util.Arrays;
import java.util.Scanner;

public class Squares {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tokens = sc.nextLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int k = Integer.parseInt(tokens[1]);
        int[] arr = new int[n];
        String[] squares = sc.nextLine().split(" ");
        int ind = 0;
        for (String square : squares) {
            arr[ind++] = Integer.parseInt(square);
        }
        Arrays.sort(arr);
        if (n - k >= 0 && n - k < arr.length) {
            System.out.println(arr[n - k] + " " + arr[n - k]);
        }
        else{
            System.out.println("-1");
        }
    }
}
