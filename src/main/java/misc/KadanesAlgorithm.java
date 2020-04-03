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

    public Triple maxSubArrayBounds(int[] nums) {
        int ans = 0; //or negative infinity depending on if the empty array is allowed or not
        int bestStart = 0;
        int bestEnd = 0;
        int currSum = 0;
        int currStart = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currSum <= 0) {
                //start a new sequence at the current element
                currStart = i;
                currSum = nums[i];
            } else {
                currSum += nums[i];
            }
            if (currSum > ans) {
                ans = currSum;
                bestStart = currStart;
                bestEnd = i + 1; //the +1 is to make 'bestEnd' exclusive
            }
        }
        return new Triple(ans, bestStart, bestEnd);
    }

    public class Triple {
        private int sum;
        private int start;
        private int end;

        public Triple(int sum, int start, int end) {
            this.sum = sum;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Triple{" +
                    "sum=" + sum +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
