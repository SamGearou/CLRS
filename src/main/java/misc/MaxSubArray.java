package misc;

public class MaxSubArray {

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(MaxSubArray.maxSum(arr));
    }

    //Kadane's Algorithm - calculates the maximum contiguous subarray sum
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > 0) {
                arr[i] += arr[i - 1];
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
        }
        return max;
    }
}
