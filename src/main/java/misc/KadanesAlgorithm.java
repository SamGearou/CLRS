package misc;

public class KadanesAlgorithm {

    //finds the max subarray sum, where you must use at least one element
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            ans = Math.max(ans, currSum);
        }
        return ans;
    }

    //finds the max subarray sum, where the empty array is valid
    public int maxSubArrayZero(int[] nums) {
        int ans = 0;
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum = Math.max(0, currSum + nums[i]);
            ans = Math.max(ans, currSum);
        }
        return ans;
    }
}
