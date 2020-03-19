package misc;

public class MaxSubArray {

    public static void main(String[] args) {

    }

    //Kadane's Algorithm - calculates the maximum contiguous subarray sum
    //This implementation returns zero for an array that contains all negative numbers
    //In order to return the smallest negative number, calculate max outside of the for loop
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Math.max(arr[0], 0);
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

    //recursive version of the maximum contiguous subarray sum
    public static int recursiveMaxSubArray(int[] arr) {
        return 0;
    }

    public int maxCrossingSubArray(int[] arr, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int maxLeft = mid;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            leftSum = Math.max(leftSum, sum);
            maxLeft = i;
        }

        int rightSum = Integer.MIN_VALUE;
        int maxRight = mid;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += arr[i];
            rightSum = Math.max(rightSum, sum);
            maxRight = 0; //TODO
        }
        return 0; //TODO
    }
}
